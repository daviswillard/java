package ex01.src;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;

    private UserIdsGenerator() {}
    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    private Integer userId = 0;

    public Integer getUserId() {
        return this.userId++;
    }
}
