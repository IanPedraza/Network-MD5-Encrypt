package model;

public class User {
    
    private String userId;
    private String userName;
    private String password;
    private String message;
    
    public User() {
        this.userId = "";
        this.userName = "";
        this.password = "";
        this.message = "";
    }

    public User(String userId, String userName, String password, String message) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                    "\n\tid: " + userId +
                    "\n\tuserName: " + userName +
                    "\n\tpassword: " + password +
                "\n}";
    }
    
    
    
}
