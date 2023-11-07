package money.manager.config.security.filter;

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
import money.manager.config.security.filter.exception.SecurityFilterException;
import money.manager.service.auth.AuthService;
import money.manager.service.auth.TokenService;
import money.manager.service.auth.dto.input.TokenServiceInputDto;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private AuthService authService;

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain filterChain) {
    try {
      final var anAuthHeader = request.getHeader("Authorization");

      if (anAuthHeader != null) {
        final var aToken = anAuthHeader.replace("Bearer ", "");
        final var aTokenInput = new TokenServiceInputDto(aToken);

        final var aTokenSubject = this.tokenService.validateToken(aTokenInput);
        final var aUsername = this.authService.loadUserByUsername(aTokenSubject.subject());
        
        final var auth = new UsernamePasswordAuthenticationToken(
            aUsername,
            null,
            aUsername.getAuthorities());

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
