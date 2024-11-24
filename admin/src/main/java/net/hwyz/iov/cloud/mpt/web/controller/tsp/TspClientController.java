package net.hwyz.iov.cloud.mpt.web.controller.tsp;

import net.hwyz.iov.cloud.mpt.common.annotation.Log;
import net.hwyz.iov.cloud.mpt.common.core.controller.BaseController;
import net.hwyz.iov.cloud.mpt.common.core.domain.AjaxResult;
import net.hwyz.iov.cloud.mpt.common.core.domain.entity.TspClient;
import net.hwyz.iov.cloud.mpt.common.core.page.TableDataInfo;
import net.hwyz.iov.cloud.mpt.common.enums.BusinessType;
import net.hwyz.iov.cloud.mpt.common.utils.poi.ExcelUtil;
import net.hwyz.iov.cloud.mpt.framework.shiro.util.AuthorizationUtils;
import net.hwyz.iov.cloud.mpt.system.service.ITspClientService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TSP客户端信息
 *
 * @author hwyz_leo
 */
@Controller
@RequestMapping("/tsp/client")
public class TspClientController extends BaseController {
    private final String prefix = "tsp/client";

    @Autowired
    private ITspClientService clientService;

    @RequiresPermissions("tsp:client:view")
    @GetMapping()
    public String account() {
        return prefix + "/client";
    }

    @RequiresPermissions("tsp:client:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TspClient client) {
        startPage();
        List<TspClient> list = clientService.selectClientList(client);
        return getDataTable(list);
    }

    @Log(title = "客户端管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("tsp:client:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TspClient client) {
        List<TspClient> list = clientService.selectClientList(client);
        ExcelUtil<TspClient> util = new ExcelUtil<>(TspClient.class);
        return util.exportExcel(list, "客户端数据");
    }

    /**
     * 查询客户端详细
     */
    @RequiresPermissions("tsp:client:list")
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("client", clientService.selectClientById(id));
        return prefix + "/view";
    }

    /**
     * 修改保存客户端
     */
    @RequiresPermissions("tsp:client:edit")
    @Log(title = "客户端管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TspClient client) {
        client.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(clientService.updateClient(client));
    }


    @RequiresPermissions("tsp:client:remove")
    @Log(title = "客户端管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(clientService.deleteClientByIds(ids));
    }

}