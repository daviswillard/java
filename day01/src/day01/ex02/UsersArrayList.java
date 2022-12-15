package day01.ex02;

public class UsersArrayList implements  UsersList {

    private User[] userList = new User[10];

    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    @Override
    public void    addUser(User client) {
        int     i;
        User[]  temp;

        for (i = 0; i < userList.length; i++) {
            if (userList[i] == null) {
                userList[i] = client;
                return ;
            }
        }
        temp = userList;
        userList = new User[userList.length / 2 * 3];
        for (int j = 0; j < i; j++) {
            userList[j] = temp[j];
        }
        userList[i] = client;
    }

    @Override
    public User    getUser(Integer id) {
        for (int i = 0; i < userList.length; i++) {
            if (id.equals(userList[i].getId())) {
                return userList[i];
            }
        }
        throw new UserNotFoundException("No User with such id");
    }

    @Override
    public User    getUserIndex(int index) {
        return userList[index];
    }
    @Override
    public int     getUserCount() {
        return userList.length;
    }
}
