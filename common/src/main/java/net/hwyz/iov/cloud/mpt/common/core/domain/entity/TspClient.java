package net.hwyz.iov.cloud.mpt.common.core.domain.entity;

import net.hwyz.iov.cloud.mpt.common.annotation.Excel;
import net.hwyz.iov.cloud.mpt.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * TSP客户端对象 tsp_client
 *
 * @author hwyz_leo
 */
public class TspClient extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 账号ID
     */
    @Excel(name = "账号ID", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.STRING, prompt = "账号ID")
    private String accountId;

    /**
     * 客户端ID
     */
    @Excel(name = "客户端ID")
    private String clientId;

    /**
     * 推送注册ID
     */
    private String pushRegId;

    /**
     * 客户端类型
     */
    @Excel(name = "客户端类型", cellType = Excel.ColumnType.TEXT)
    private String clientType;

    /**
     * 设备厂商
     */
    @Excel(name = "设备厂商")
    private String oem;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 操作系统版本
     */
    private String osVersion;

    /**
     * 应用版本
     */
    @Excel(name = "应用版本")
    private String appVersion;

    /**
     * IP
     */
    private String ip;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间")
    private Date loginTime;

    /**
     * 是否有效
     */
    private Boolean rowValid;

    public TspClient() {

    }

    public TspClient(String accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPushRegId() {
        return pushRegId;
    }

    public void setPushRegId(String pushRegId) {
        this.pushRegId = pushRegId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getRowValid() {
        return rowValid;
    }

    public void setRowValid(Boolean rowValid) {
        this.rowValid = rowValid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("accountId", getAccountId())
                .append("clientId", getClientId())
                .append("pushRegId", getPushRegId())
                .append("clientType", getClientType())
                .append("oem", getOem())
                .append("os", getOs())
                .append("osVersion", getOsVersion())
                .append("appVersion", getAppVersion())
                .append("ip", getIp())
                .append("loginTime", getLoginTime())
                .append("rowValid", getRowValid())
                .toString();
    }
}
