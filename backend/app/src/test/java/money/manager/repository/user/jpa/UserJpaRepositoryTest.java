package money.manager.repository.user.jpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import money.manager.domain.user.User;


@DataJpaTest
@ActiveProfiles("test")
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userRepository;

    private UserJpaEntity user;

    @BeforeEach
    void setup() {
        final var aUserModel = User.newUser(
                "Limão", 
                "limao@gmail.com",
                "123456");
        user = UserJpaEntity.from(aUserModel);
    }

    @Test
    @DisplayName("Given Username When Finding User By Username Then Get User Successfully From DB")
    void testFindByUsernameSuccess() {
    
        this.userRepository.save(user);
        final var aSearch = this.userRepository.findByUsername(user.getUsername());

        assertNotNull(aSearch, "User not should return null.");
        assertEquals(user.getUsername(), aSearch.getUsername(), "User should returned same username.");
    }

    @Test
    @DisplayName("Given Non-Existent Username When Finding User By Username Then Return Null")
    void testFindByUsernameFailure() {
        
        final var aNonExistentUsername = "Maxixe";
        
        this.userRepository.save(user);
        final var aSearch = this.userRepository.findByUsername(aNonExistentUsername);

        assertNull(aSearch, "User should return null.");
    }

}
