package money.manager.domain.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import money.manager.domain.exception.DomainException;
import money.manager.utils.UUIDUtils;

public class User implements UserDetails {

  private String id;
  private String username;
  private String email;
  private String password;

  private User(final String anId, final String aUsername, final String anEmail, final String aPassword) {

    this.username = aUsername;
    this.email = anEmail;
    this.password = aPassword;
    this.id = anId;

    this.validate();
  }

  
  public static User newUser(final String aUsername, final String anEmail, final String aPassword) {
    return new User(UUIDUtils.generate(), aUsername, anEmail, aPassword);
  }

  public static User with(final String anId, final String aUsername, final String anEmail, final String aPassword) {
    return new User(anId, aUsername, anEmail, aPassword);
  }

  private void validate() {
    if (this.id.length() != 36)
      throw new DomainException("Invalid User UUID.");

    if (this.username.length() < 3 || this.username.length() > 32)
      throw new DomainException("Username shoud have between 3 to 32 characters.");

    if (this.username.isBlank())
      throw new DomainException("Username should not be blank.");

    if (this.email.isBlank())
      throw new DomainException("User e-mail should not be blank.");

    if (this.password.isBlank())
      throw new DomainException("User password should not be blank");

    if (this.password.length() < 6)
      throw new DomainException("User password shoud be at least 5 characters.");

  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("USER "));
  }

  @Override
  public String getUsername() {
    return this.username;
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
