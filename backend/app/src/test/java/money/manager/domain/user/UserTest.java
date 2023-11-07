package money.manager.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import money.manager.domain.exception.DomainException;

public class UserTest {

    @Test
    @DisplayName("Given Data When Called NewUser Method Then Return User Model Successfully.")
    void testCreateNewUserSuccessfully() {
        
        final var aUsername = "Limão";
        final var anEmail = "limao@gmail.com";
        final var aPassword = "123456";

        final var aUser = User.newUser(aUsername, anEmail, aPassword);

        assertNotNull(aUser, "Not should null.");
        assertEquals(aUsername, aUser.getUsername(), "Username should equal to input data.");
        assertEquals(anEmail, aUser.getEmail(), "E-mail should equal to input data.");
        assertEquals(aPassword, aUser.getPassword(), "Password should equal to input data.");
    }

    @Test
    @DisplayName("Given Balnk Username When NewUser Method Then Throw DomainException.")
    void testNewUserWithBlankUsernameThrowDomainException() {
        
        Assertions.assertThrows(DomainException.class, () -> User.newUser("", "limao@gmail.com", "123456"));
    }

    @Test
    @DisplayName("Given Blank E-mail When NewUser Method Then Throw DomainException.")
    void testNewUserWithBlankEmailThrowDomainException() {
        
        Assertions.assertThrows(DomainException.class, () -> User.newUser("Limão", "", "123456"));
    }

}
