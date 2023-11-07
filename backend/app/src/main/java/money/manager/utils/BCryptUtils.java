package money.manager.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

public class BCryptUtils {
      
      public static Result passwordVerify(final String aSource, final String aTarget){
            final var aVerify =  BCrypt.verifyer().verify(aSource.toCharArray(), aTarget);
            return aVerify;
      }

}
