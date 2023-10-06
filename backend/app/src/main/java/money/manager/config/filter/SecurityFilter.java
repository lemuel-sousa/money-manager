package money.manager.config.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import money.manager.config.filter.exception.SecurityFilterException;
import money.manager.service.auth.AuthService;
import money.manager.service.auth.dto.ValidateServiceInputDto;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private AuthService authService;

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain filterChain) {

    try {
      final var anAuthToken = request.getHeader("Authorization");

      if (anAuthToken != null) {
        final var aToken = anAuthToken.replace("Bearer ", "");
        final var aTokenInput = new ValidateServiceInputDto(aToken);

        final var anUsername = this.authService.validateToken(aTokenInput);
        final var anUser = this.authService.loadUserByUsername(anUsername);

        final var auth = new UsernamePasswordAuthenticationToken(
            anUser,
            null,
            anUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
      }

      filterChain.doFilter(request, response);

    } catch (ServletException e) {
      throw new SecurityFilterException(e.getMessage());
    } catch (IOException e) {
      throw new SecurityFilterException(e.getMessage());
    }
  }

}
