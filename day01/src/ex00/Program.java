package ex00;

public class Program {

    public static void main(String[] args) {
        User    kamil = new User("Kamil", 1, 4000);
        User    ramil = new User("Ramil", 2, 5000);
        User    shamil = new User("Shamil", 3, 2500);
        User    emil = new User("Emil", 4, -200);

        {
            Transaction ramKam = new Transaction(ramil, kamil, -200);
            System.out.println(ramKam.getAmount());
            System.out.println(ramKam.getCategory());
            System.out.println(ramKam.getId());
        }
        {
            Transaction shamKam = new Transaction(shamil, kamil, 200);
            System.out.println(shamKam.getAmount());
            System.out.println(shamKam.getCategory());
            System.out.println(shamKam.getId());
        }
        {
            Transaction kamEm = new Transaction(emil, kamil, -200);
            System.out.println(kamEm.getAmount());
            System.out.println(kamEm.getCategory());
            System.out.println(kamEm.getId());
        }
    }
}
