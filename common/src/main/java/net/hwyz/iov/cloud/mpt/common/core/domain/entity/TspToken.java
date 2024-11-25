package net.hwyz.iov.cloud.mpt.common.core.domain.entity;

import net.hwyz.iov.cloud.mpt.common.annotation.Excel;
import net.hwyz.iov.cloud.mpt.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * TSP令牌对象 tsp_token
 *
 * @author hwyz_leo
 */
public class TspToken extends BaseEntity {
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
     * 客户端类型
     */
    @Excel(name = "客户端类型", cellType = Excel.ColumnType.TEXT)
    private String clientType;

    /**
     * 客户端ID
     */
    @Excel(name = "客户端ID")
    private String clientId;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 权限范围
     */
    @Excel(name = "权限范围")
    private String scope;

    /**
     * 发行时间
     */
    private Date issueTime;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 访问令牌过期时间
     */
    @Excel(name = "访问令牌过期时间")
    private Date accessTokenExpires;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌过期时间
     */
    @Excel(name = "刷新令牌过期时间")
    private Date refreshTokenExpires;

    /**
     * 是否有效
     */
    private Boolean rowValid;

    public TspToken() {

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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenExpires() {
        return accessTokenExpires;
    }

    public void setAccessTokenExpires(Date accessTokenExpires) {
        this.accessTokenExpires = accessTokenExpires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(Date refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
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
                .append("clientType", getClientType())
                .append("clientId", getClientId())
                .append("vin", getVin())
                .append("scope", getScope())
                .append("issueTime", getIssueTime())
                .append("accessToken", getAccessToken())
                .append("accessTokenExpires", getAccessTokenExpires())
                .append("refreshToken", getRefreshToken())
                .append("refreshTokenExpires", getRefreshTokenExpires())
                .append("rowValid", getRowValid())
                .toString();
    }
}
