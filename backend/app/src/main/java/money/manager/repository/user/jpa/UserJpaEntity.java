package money.manager.repository.user.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import money.manager.domain.user.User;

@Entity(name = "Users")
@Table(name = "users")
public class UserJpaEntity {

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "username", nullable = false)
  private String username;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "password", nullable = false)
  private String password;

  public UserJpaEntity() {

  }

  private UserJpaEntity(final String anId, final String aUsername, final String anEmail, final String aPassword) {
    this.id = anId;
    this.username = aUsername;
    this.email = anEmail;
    this.password = aPassword;
  }

  public static UserJpaEntity from(final User anInput) {
    return new UserJpaEntity(
        anInput.getId(),
        anInput.getUsername(),
        anInput.getEmail(),
        anInput.getPassword());
  }

  public User toModel() {
    return User.with(
        this.id,
        this.username,
        this.email,
        this.password);
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserJpaEntity other = (UserJpaEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
