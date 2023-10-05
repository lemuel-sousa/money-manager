package money.manager.domain.user;

import money.manager.domain.exception.DomainException;

public class User {

  private String email;
  private String password;

  private User(final String anEmail, final String aPassword) {
    this.email = anEmail;
    this.password = aPassword;

    this.validate();
  }

  public static User with(final String anEmail, final String aPassword) {
    return new User(anEmail, aPassword);
  }

  private void validate() {
    if (this.email.isBlank()) {
      throw new DomainException("User e-mail should not be blank.");
    }
    if (this.password.isBlank()) {
      throw new DomainException("User password should not be blank");
    }
    if (this.password.length() < 6) {
      throw new DomainException("User password shoud be at least 5 characters.");
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
