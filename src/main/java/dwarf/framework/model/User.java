package dwarf.framework.model;

public class User {

    private final Integer userId;
    private final String username;

    public User(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
