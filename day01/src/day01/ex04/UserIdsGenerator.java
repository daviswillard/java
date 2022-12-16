package day01.ex04;

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

    public Integer generateId() {
        return this.userId++;
    }
}
