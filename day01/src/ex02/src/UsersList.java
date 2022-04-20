package ex02.src;

public interface UsersList {
    void    addUser(User client);
    User    getUser(Integer id);
    User    getUserIndex(int index);
    int     getUserCount();
}
