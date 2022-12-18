package day01.ex05.userslist;

import day01.ex05.models.User;

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class UsersArrayList implements UsersList {

    private User[] userList = new User[10];

    @Override
    public void    addUser(User client) {
        int i;
        User[]  temp;

        for (i = 0; i < userList.length; i++) {
            if (userList[i] == null) {
                userList[i] = client;
                return;
            } else if (userList[i] == client) {
                System.out.println("User " + client.getName() + " already present!");
                return;
            }
        }
        temp = userList;
        userList = new User[(int)(userList.length / 2 * 3)];
        for (int j = 0; j < i; j++) {
            userList[j] = temp[j];
        }
        userList[i] = client;
    }

    @Override
    public User getUser(Integer id) {
        for (User user : userList) {
            if (user == null) {
                break;
            }
            if (id.equals(user.getId())) {
                return user;
            }
        }
        throw new UserNotFoundException("No User with such id");
    }

    @Override
    public User getUserIndex(int index) {
        return userList[index];
    }
    @Override
    public int     getUserCount() {
        int i = 0;
        while (i < userList.length && userList[i] != null) {
            i++;
        }
        return i;
    }
}
