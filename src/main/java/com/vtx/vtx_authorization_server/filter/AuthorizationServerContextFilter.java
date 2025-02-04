package com.vtx.vtx_authorization_server.filter;

import com.vtx.vtx_authorization_server.context.AuthorizationServerContext;
import com.vtx.vtx_authorization_server.context.AuthorizationServerContextHolder;
import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AuthorizationServerContextFilter extends OncePerRequestFilter implements OrderedFilter {

  private final AuthorizationServerSettings authorizationServerSettings;
  private final IssuerResolver issuerResolver;

  public AuthorizationServerContextFilter(AuthorizationServerSettings authorizationServerSettings) {
    this.authorizationServerSettings = authorizationServerSettings;
    this.issuerResolver = new IssuerResolver(authorizationServerSettings);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String issuer = issuerResolver.resolve(request);
      AuthorizationServerContext authorizationServerContext = new DefaultAuthorizationServerContext(issuer, authorizationServerSettings);
      AuthorizationServerContextHolder.setContext(authorizationServerContext);
      filterChain.doFilter(request, response);
    } finally {
      AuthorizationServerContextHolder.removeContext();
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !"/.well-known/openid-configuration".equals(request.getRequestURI());
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  private static final class IssuerResolver {
    private final String issuer;

    private final Set<String> endpointUris;

    public IssuerResolver(AuthorizationServerSettings authorizationServerSettings) {
      if (authorizationServerSettings.getIssuer() != null) {
        this.issuer = authorizationServerSettings.getIssuer();
        this.endpointUris = new HashSet<>();
      } else {
        this.issuer = null;
        this.endpointUris = new HashSet<>();
        this.endpointUris.add("/.well-known/oauth-authorization-server");
        this.endpointUris.add("/.well-known/openid-configuration");
      }
    }

    private String resolve(HttpServletRequest request) {
      if (this.issuer != null) {
        return this.issuer;
      }

      String path = request.getRequestURI();

      if (!StringUtils.hasText(path)) {
        path = "";
      } else {
        for (String endpointUri : this.endpointUris) {
          if (path.contains(endpointUri)) {
            path = path.replace(endpointUri, "");
            break;
          }
        }
      }

      return UriComponentsBuilder.fromUriString(request.getRequestURL().toString())
              .replacePath(path)
              .replaceQuery(null)
              .fragment(null)
              .build()
              .toUriString();
    }
  }

  private static final class DefaultAuthorizationServerContext implements AuthorizationServerContext {

    private final String issuer;

    private final AuthorizationServerSettings authorizationServerSettings;

    public DefaultAuthorizationServerContext(String issuer, AuthorizationServerSettings authorizationServerSettings) {
      this.issuer = issuer;
      this.authorizationServerSettings = authorizationServerSettings;
    }

    @Override
    public String getIssuer() {
      return issuer;
    }

    @Override
    public AuthorizationServerSettings getAuthorizationServerSettings() {
      return authorizationServerSettings;
    }
  }
}
