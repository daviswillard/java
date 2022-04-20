package ex03.src;

public class Program {

    public static void main(String[] args) {
        User kamil = new User("Kamil", 4000);
        User ramil = new User("Ramil", 5000);
        User shamil = new User("Shamil",  2500);
        User emil = new User("Emil", -200);
        Transaction[] arr;

        System.out.println(kamil.getId() + "\n" + kamil.getName());
        System.out.println(ramil.getId() + "\n" + ramil.getName());
        kamil.transList.addTransaction(new Transaction(kamil, ramil, 500));
        ramil.transList.addTransaction(new Transaction(ramil, kamil, -500));
        kamil.transList.addTransaction(new Transaction(kamil, shamil, 2000));
        kamil.transList.addTransaction(new Transaction(kamil, emil, -500));
        arr = kamil.transList.getArray();
        for (int i = 0; i < kamil.transList.getLen(); i++) {
            System.out.println(arr[i].getAmount());
            System.out.println(arr[i].getId());
            System.out.println(arr[i].getCategory());
        }

        {
            kamil.transList.removeTransaction(arr[1].getId());
            System.out.println("--------------");
            arr = kamil.transList.getArray();
            for (int i = 0; i < kamil.transList.getLen(); i++) {
                System.out.println(arr[i].getAmount());
                System.out.println(arr[i].getId());
                System.out.println(arr[i].getCategory());
            }
        }
    }
}
