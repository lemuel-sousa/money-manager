package money.manager.repository.user;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import money.manager.domain.gateway.UserGateway;
import money.manager.domain.user.User;
import money.manager.repository.exception.PersistenceException;
import money.manager.repository.user.jpa.UserJpaEntity;
import money.manager.repository.user.jpa.UserJpaRepository;

@Component
public class UserJpaGateway implements UserGateway {

  private UserJpaRepository userRepository;

  private UserJpaGateway(final UserJpaRepository aRepository) {
    this.userRepository = aRepository;
  }

  public static UserJpaGateway build(final UserJpaRepository aRepository) {
    return new UserJpaGateway(aRepository);
  }

  @Override
  public User findByUsername(final String aUsername) {
    final var aUser = this.userRepository.findByUsername(aUsername);

    if (aUser != null)
      return aUser.toModel();
    else
      return null;
  }

  @Override
  public void save(final User anInput) {
    try {
      this.userRepository.save(UserJpaEntity.from(anInput));
    } 
    catch (IllegalArgumentException e) {
      throw new PersistenceException(e.getMessage());
    } 
    catch (OptimisticLockingFailureException e) {
      throw new PersistenceException(e.getMessage());
    }
  }

}
