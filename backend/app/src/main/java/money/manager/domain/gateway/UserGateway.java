package money.manager.domain.gateway;

import money.manager.domain.user.User;

public interface UserGateway {

  User findByUsername(final String aUsername);

  void save(final User anInput);

}
