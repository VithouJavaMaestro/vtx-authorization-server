package com.vtx.vtx_authorization_server.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class OAuth2TokenEndpointFilter extends OncePerRequestFilter implements Ordered {
//  private final RequestMatcher matcher;
//
//  public OAuth2TokenEndpointFilter(AuthorizationServerSettings authorizationServerSettings) {
//    this.matcher = AntPathRequestMatcher.antMatcher(authorizationServerSettings.getTokenEndpoint(), HttpMethod.POST);
//  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    try {
//      String[] grantTypes = request.getParameterValues(OAuth2ParameterNames.GRANT_TYPE);
//      if (grantTypes == null || grantTypes.length != 1) {
//
//      }
//    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//    return !matcher.matches(request);
    return true;
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
