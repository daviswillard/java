package student.dwillard.models;

import student.dwillard.annotations.HtmlForm;
import student.dwillard.annotations.HtmlInput;

@HtmlForm(fileName = "user_form.html", action = "/users", method = "post")
public class UserFrom {
  @HtmlInput(type = "text", name = "first_name", placeholder = "Enter First Name")
  private String firstName;

  @HtmlInput(type = "text", name = "last_name", placeholder = "Enter Last Name")
  private String lastName;

  @HtmlInput(type = "password", name = "password", placeholder = "Enter Password")
  private String password;
}
