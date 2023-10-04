package money.manager.repository.exception;

public class PersistenceException extends RuntimeException {

  public PersistenceException(final String aMessage) {
    super(aMessage);
  }

}
