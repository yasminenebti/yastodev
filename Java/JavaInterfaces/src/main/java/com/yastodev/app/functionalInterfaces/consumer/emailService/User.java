package com.yastodev.app.functionalInterfaces.consumer.emailService;

public class User {
    private String name;
    private String email;
    private boolean isEmailVerified;

    public User(String name, String email, boolean isEmailVerified) {
        this.name = name;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                '}';
    }
}
