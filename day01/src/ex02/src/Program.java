package ex02.src;

public class Program {

    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", -200);

        list.addUser(kamil);
        list.addUser(ramil);
        list.addUser(shamil);
        list.addUser(emil);
        for (int i = 0; i < 10; i++) {
            list.addUser(new User(i + "mil", 0));
        }
        System.out.println(list.getUserCount());
        for (int i = 0; i < list.getUserCount() - 1; i++) {
            System.out.println(list.getUser(i).getName());
        }
        System.out.println("-------------");
        for (int i = 0; i < list.getUserCount() - 1; i++) {
            System.out.println(list.getUserIndex(i).getName());
        }
    }
}
