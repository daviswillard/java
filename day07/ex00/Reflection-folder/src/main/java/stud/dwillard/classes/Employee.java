package stud.dwillard.classes;

public class Employee {
    private final String name;
    private String company;
    private Double salary;
    private Boolean isEmployed;

    public Employee() {
        name = "Default";
        company = "";
        isEmployed = false;
        salary = 5000.0;
    }

    public Employee(String name, String company, Double salary) {
        this.name = name;
        isEmployed = !company.isEmpty();
        this.company = company;
        this.salary = salary;
    }

    public void changeWorkingPlace(String newCompany) {
        company = newCompany;
    }

    public void changeWorkingPlace(String newCompany, Double newSalary) {
        company = newCompany;
        salary = newSalary;
    }
}
