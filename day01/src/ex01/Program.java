package ex01;

public class Program {

    public static void main(String[] args) {
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", -200);

        System.out.println(kamil.getId() + "\n" + kamil.getName());
        System.out.println(ramil.getId() + "\n" + ramil.getName());
        System.out.println(shamil.getId() + "\n" + shamil.getName());
        System.out.println(emil.getId() + "\n" + emil.getName());
    }
}
