package money.manager.repository.user.jpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import money.manager.domain.user.User;

public class UserJpaEntityTest {
    
    @Test
    @DisplayName("Given User Model When Called From Then Return Successfully User Entity Jpa")
    void testCreateUserJpaEntityFromUserModelSuccessfully() {

        final var aUserModel = User.newUser("Limão", "limao@gmail.com","123456");

        final var aJpaEntity = UserJpaEntity.from(aUserModel);

        assertNotNull(aJpaEntity, "User Jpa Entity not should return null.");
        assertEquals(aJpaEntity.getId(), aUserModel.getId(), "User Jpa Entity UUID should be equals to User model.");
        assertEquals(aJpaEntity.getUsername(), aUserModel.getUsername(), "User Jpa Entity username should be equals to User model username.");
        assertEquals(aJpaEntity.getEmail(), aUserModel.getEmail(), "User Jpa Entity e-mail should be equals to User model e-mail.");
        assertEquals(aJpaEntity.getPassword(), aUserModel.getPassword(), "User Jpa Entity password should be equals to User model password.");
    }

}
