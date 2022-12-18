package day01.ex05.userslist;

import day01.ex05.models.User;

public interface UsersList {
    void    addUser(User client);
    User getUser(Integer id);
    User getUserIndex(int index);
    int     getUserCount();
}
