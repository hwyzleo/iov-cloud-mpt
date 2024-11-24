package net.hwyz.iov.cloud.mpt.common.core.domain.entity;

import net.hwyz.iov.cloud.mpt.common.annotation.Excel;
import net.hwyz.iov.cloud.mpt.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TSP账号对象 tsp_account
 *
 * @author hwyz_leo
 */
public class TspAccount extends BaseEntity {
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
     * 用户名
     */
    @Excel(name = "用户名")
    private String username;

    /**
     * 手机所属国家或地区
     */
    @Excel(name = "手机所属国家或地区", cellType = Excel.ColumnType.TEXT)
    private String countryRegionCode;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", cellType = Excel.ColumnType.TEXT)
    private String mobile;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String gender;

    /**
     * 注册来源
     */
    private String regSource;

    /**
     * 是否启用
     */
    @Excel(name = "是否启用")
    private Boolean enable;

    /**
     * 是否有效
     */
    private Boolean rowValid;

    public TspAccount() {

    }

    public TspAccount(String accountId) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountryRegionCode() {
        return countryRegionCode;
    }

    public void setCountryRegionCode(String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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
                .append("username", getUsername())
                .append("countryRegionCode", getCountryRegionCode())
                .append("mobile", getMobile())
                .append("email", getEmail())
                .append("nickname", getNickname())
                .append("avatar", getAvatar())
                .append("gender", getGender())
                .append("regSource", getRegSource())
                .append("enable", getEnable())
                .append("rowValid", getRowValid())
                .toString();
    }
}
