package money.manager.repository.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import money.manager.domain.user.User;
import money.manager.repository.user.jpa.UserJpaEntity;
import money.manager.repository.user.jpa.UserJpaRepository;

public class UserJpaGatewayTest {

    @Mock
    private UserJpaRepository userRepository;

    @InjectMocks
    private UserJpaGateway userJpaGateway;

    private User user;

    @BeforeEach
    void setup() {
        user = User.newUser(
                "Limão",
                "limao@gmail.com",
                "123456");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given Username When Finding User By Username Then Return Successfully User")
    void testFindByUsernameSuccess() {
        // Arrange
        final var aUsername = user.getUsername();
        when(this.userRepository.findByUsername(aUsername)).thenReturn(UserJpaEntity.from(user));

        // Act
        final var aResult = this.userJpaGateway.findByUsername(aUsername);

        // Assert
        assertNotNull(aResult, "User not should return null.");
        assertEquals(aResult.getUsername(), aUsername);
    }

    @Test
    @DisplayName("Given Username When Finding User By Username Then Return Null")
    void testFindByUsernameFailure() {
        // Arrange
        final var aUsername = "non-existent_user";
        when(this.userRepository.findByUsername(aUsername)).thenReturn(null);

        // Act
        final var aResult = this.userJpaGateway.findByUsername(aUsername);

        // Assert
        assertNull(aResult, "User should return null.");
    }

    @Test
    @DisplayName("Given User When Save Is Called Then Return Successfully User")
    void testSaveSuccessfully() {
        // Arrange
        final var aUser = UserJpaEntity.from(user);
        when(this.userRepository.save(aUser)).thenReturn(any(UserJpaEntity.class));

        // Act
        assertDoesNotThrow(() -> userJpaGateway.save(user));
    }

}
