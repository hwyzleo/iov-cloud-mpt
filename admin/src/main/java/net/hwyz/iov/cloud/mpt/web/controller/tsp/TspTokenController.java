package net.hwyz.iov.cloud.mpt.web.controller.tsp;

import net.hwyz.iov.cloud.mpt.common.annotation.Log;
import net.hwyz.iov.cloud.mpt.common.core.controller.BaseController;
import net.hwyz.iov.cloud.mpt.common.core.domain.AjaxResult;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspToken;
import net.hwyz.iov.cloud.mpt.common.core.page.TableDataInfo;
import net.hwyz.iov.cloud.mpt.common.enums.BusinessType;
import net.hwyz.iov.cloud.mpt.common.utils.poi.ExcelUtil;
import net.hwyz.iov.cloud.mpt.system.service.ITspTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TSP令牌信息
 *
 * @author hwyz_leo
 */
@Controller
@RequestMapping("/tsp/token")
public class TspTokenController extends BaseController {
    private final String prefix = "tsp/token";

    @Autowired
    private ITspTokenService tokenService;

    @RequiresPermissions("tsp:token:view")
    @GetMapping()
    public String token() {
        return prefix + "/token";
    }

    @RequiresPermissions("tsp:token:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TspToken token) {
        startPage();
        List<TspToken> list = tokenService.selectTokenList(token);
        return getDataTable(list);
    }

    @Log(title = "令牌管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("tsp:token:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TspToken token) {
        List<TspToken> list = tokenService.selectTokenList(token);
        ExcelUtil<TspToken> util = new ExcelUtil<>(TspToken.class);
        return util.exportExcel(list, "令牌数据");
    }

    /**
     * 查询令牌详细
     */
    @RequiresPermissions("tsp:token:list")
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("token", tokenService.selectTokenById(id));
        return prefix + "/view";
    }

    /**
     * 修改保存令牌
     */
    @RequiresPermissions("tsp:token:edit")
    @Log(title = "令牌管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TspToken token) {
        token.setUpdateBy(getLoginName());
        return toAjax(tokenService.updateToken(token));
    }


    @RequiresPermissions("tsp:token:remove")
    @Log(title = "令牌管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tokenService.deleteTokenByIds(ids));
    }

}