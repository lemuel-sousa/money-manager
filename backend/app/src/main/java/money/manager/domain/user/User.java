package money.manager.domain.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import money.manager.domain.exception.DomainException;

public class User implements UserDetails {

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("USER "));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
