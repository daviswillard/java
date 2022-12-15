package day01.ex02;

public interface UsersList {
    void    addUser(User client);
    User    getUser(Integer id);
    User    getUserIndex(int index);
    int     getUserCount();
}
