package com.herotozero.bean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn = false;
    private boolean admin = false;

    public String login() {
        if ("admin".equals(username) && "1234".equals(password)) {
            loggedIn = true;
            admin = true;
            return "/admin/approve.xhtml?faces-redirect=true";
        } else if ("scientist".equals(username) && "1234".equals(password)) {
            loggedIn = true;
            admin = false;
            return "/index.xhtml?faces-redirect=true";
        }
        return null;
    }

    public String logout() {
        loggedIn = false;
        admin = false;
        username = null;
        password = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLoggedIn() { return loggedIn; }
    public boolean isAdmin() { return admin; }
}
