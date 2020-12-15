package org.banana.authserver.domain.model;

/**
 * @author yuanrui@live.cn
 * @since 2020/11/30 15:57
 */
public class UserModel {
    public final static String USER_SESSION = "USER_SESSION";
    private String name;
    private String realName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    private String roleId;

}
