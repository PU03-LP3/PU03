package Models;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private boolean isAdmin;
    private java.util.Date createdAt;

    public User() {}

    public User(int id, String fullName, String email, String password, boolean isAdmin, java.util.Date createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }
}