package org.banana.authserver.domain;

/**
 * @author yuanrui@live.cn
 * @since 2020/11/30 15:54
 */
public class LoginModel {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
