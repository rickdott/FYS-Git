package com.mycompany.mavenproject2;

/**
 * UserDetails class, to show different users registered to the application and 
 * to enable editing their information
 * @author Tarik Yildirim 500780772 (67 Lines)
 */
public class UserDetails {
    private String editUsername;
    private String firstnameTc;
    private String lastnameTc;
    private String usernameTc;
    private String passwordTc;
    private int roleTc;

    public UserDetails() {
        this.roleTc = 0;
    }
    public String geteditUsername(){
        return editUsername;
    }
    
     public void seteditUsername(String editUsername){
       this.editUsername = editUsername;
    }
    
    public String getFirstnameTc() {
        return firstnameTc;
    }

    public void setFirstnameTc(String firstnameTc) {
        this.firstnameTc = firstnameTc;
    }

    public String getLastnameTc() {
        return lastnameTc;
    }

    public void setLastnameTc(String lastnameTc) {
        this.lastnameTc = lastnameTc;
    }

    public String getUsernameTc() {
        return usernameTc;
    }

    public void setUsernameTc(String usernameTc) {
        this.usernameTc = usernameTc;
    }

    public String getPasswordTc() {
        return passwordTc;
    }

    public void setPasswordTc(String passwordTc) {
        this.passwordTc = passwordTc;
    }

    public int getRoleTc() {
        return roleTc;
    }

    public void setRoleTc(int roleTc) {
        this.roleTc = roleTc;
    }
}
