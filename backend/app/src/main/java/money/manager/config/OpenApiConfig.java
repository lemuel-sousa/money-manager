package money.manager.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
      
      public OpenAPI OpenApiSetup() {
            return new OpenAPI()
                        .info(new Info().title("money-manager")
                                          .description("REST API for clash flow management")
                                          .version("1.0.0"));
      }
}
