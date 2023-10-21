package money.manager.repository.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {

  UserJpaEntity findByUsername(final String aUsername);

}
