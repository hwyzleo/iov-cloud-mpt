package net.hwyz.iov.cloud.mpt.web.controller.tsp;

import net.hwyz.iov.cloud.mpt.common.annotation.Log;
import net.hwyz.iov.cloud.mpt.common.core.controller.BaseController;
import net.hwyz.iov.cloud.mpt.common.core.domain.AjaxResult;
import net.hwyz.iov.cloud.mpt.common.core.domain.Ztree;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.SysDept;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.SysRole;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.SysUser;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspAccount;
import net.hwyz.iov.cloud.mpt.common.core.page.TableDataInfo;
import net.hwyz.iov.cloud.mpt.common.core.text.Convert;
import net.hwyz.iov.cloud.mpt.common.enums.BusinessType;
import net.hwyz.iov.cloud.mpt.common.utils.DateUtils;
import net.hwyz.iov.cloud.mpt.common.utils.ShiroUtils;
import net.hwyz.iov.cloud.mpt.common.utils.StringUtils;
import net.hwyz.iov.cloud.mpt.common.utils.poi.ExcelUtil;
import net.hwyz.iov.cloud.mpt.framework.shiro.service.SysPasswordService;
import net.hwyz.iov.cloud.mpt.framework.shiro.util.AuthorizationUtils;
import net.hwyz.iov.cloud.mpt.system.service.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TSP账号信息
 * 
 * @author hwyz_leo
 */
@Controller
@RequestMapping("/tsp/account")
public class TspAccountController extends BaseController
{
    private final String prefix = "tsp/account";

    @Autowired
    private ITspAccountService accountService;

    @Autowired
    private ISysRoleService roleService;
    
    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("tsp:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    @RequiresPermissions("tsp:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TspAccount account)
    {
        startPage();
        List<TspAccount> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }

    @Log(title = "账号管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("tsp:account:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TspAccount user)
    {
        List<TspAccount> list = accountService.selectAccountList(user);
        ExcelUtil<TspAccount> util = new ExcelUtil<>(TspAccount.class);
        return util.exportExcel(list, "账号数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("tsp:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String message = accountService.importUser(userList, updateSupport, getLoginName());
        return AjaxResult.success(message);
    }

    @RequiresPermissions("tsp:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("tsp:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user)
    {
        deptService.checkDeptDataScope(user.getDeptId());
        roleService.checkRoleDataScope(user.getRoleIds());
        if (!accountService.checkLoginNameUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !accountService.checkPhoneUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !accountService.checkEmailUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setPwdUpdateDate(DateUtils.getNowDate());
        user.setCreateBy(getLoginName());
        return toAjax(accountService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("tsp:user:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", accountService.selectUserById(userId));
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 查询用户详细
     */
    @RequiresPermissions("tsp:user:list")
    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", accountService.selectUserById(userId));
        mmap.put("roleGroup", accountService.selectUserRoleGroup(userId));
        mmap.put("postGroup", accountService.selectUserPostGroup(userId));
        return prefix + "/view";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("tsp:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user)
    {
        accountService.checkUserAllowed(user);
        deptService.checkDeptDataScope(user.getDeptId());
        roleService.checkRoleDataScope(user.getRoleIds());
        if (!accountService.checkLoginNameUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !accountService.checkPhoneUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !accountService.checkEmailUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(accountService.updateUser(user));
    }

    @RequiresPermissions("tsp:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", accountService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("crm:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user)
    {
        accountService.checkUserAllowed(user);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (accountService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
            {
                setSysUser(accountService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        SysUser user = accountService.selectUserById(userId);
        // 获取用户所属的角色列表
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("tsp:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        roleService.checkRoleDataScope(roleIds);
        accountService.insertUserAuth(userId, roleIds);
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return success();
    }

    @RequiresPermissions("tsp:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId()))
        {
            return error("当前用户不能删除");
        }
        return toAjax(accountService.deleteUserByIds(ids));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public boolean checkLoginNameUnique(SysUser user)
    {
        return accountService.checkLoginNameUnique(user);
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public boolean checkPhoneUnique(SysUser user)
    {
        return accountService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public boolean checkEmailUnique(SysUser user)
    {
        return accountService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("tsp:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user)
    {
        accountService.checkUserAllowed(user);
        return toAjax(accountService.changeStatus(user));
    }

    /**
     * 加载部门列表树
     */
    @RequiresPermissions("tsp:user:list")
    @GetMapping("/deptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }

    /**
     * 选择部门树
     * 
     * @param deptId 部门ID
     */
    @RequiresPermissions("tsp:user:list")
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/deptTree";
    }
}