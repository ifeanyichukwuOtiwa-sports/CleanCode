package io.gxstar.cleancode.refactoring.lock;

public class User {

    String userId;

    public User(String userId) {
        super();
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
