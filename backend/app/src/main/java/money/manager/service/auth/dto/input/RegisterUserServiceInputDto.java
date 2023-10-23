package money.manager.service.auth.dto.input;

public class RegisterUserServiceInputDto {

        String username;
        String email;
        String password;

        public RegisterUserServiceInputDto(final String aUsername, final String anEmail, final String aPassword) {
                this.username = aUsername;
                this.email = anEmail;
                this.password = aPassword;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

}
