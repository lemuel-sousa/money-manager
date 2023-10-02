package money.manager.utils;

import java.util.UUID;

public class UUIDUtils {

  public static String generate() {
    return UUID.randomUUID().toString().toLowerCase();
  }

}
