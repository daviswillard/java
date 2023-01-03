package student.dwillard.classes;

public class Family {
  private String familyName;
  private Integer members;
  private Integer male;
  private Integer female;

  public Family() {
    familyName = "Smith";
    members = 2;
    male = 1;
    female = 1;
  }

  public Family(String familyName, Integer male, Integer female) {
    if (male < 0 || female < 0 || (male == 0 && female == 0)) {
      throw new RuntimeException("Can't have this kind of family!");
    }
    this.male = male;
    this.familyName = familyName;
    this.female = female;
    members = male + female;
  }

  public void grow() {
    System.out.println("A new child was born in " + familyName + " family!");
    members++;
    if (male > female && male % 3 != 0) {
      female++;
    } else {
      male++;
    }
  }

  @Override
  public String toString() {
    return String.format("Family %s has %d members.\n Consists of %d male and %d female people.",
        familyName, members, male, female);
  }
}
