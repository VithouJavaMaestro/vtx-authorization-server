package com.vtx.vtx_authorization_server.filter;import com.vtx.vtx_authorization_server.authorization.oidc.OidcProviderConfiguration;import com.vtx.vtx_authorization_server.context.AuthorizationServerContext;import com.vtx.vtx_authorization_server.context.AuthorizationServerContextHolder;import com.vtx.vtx_authorization_server.core.AuthorizationGrantType;import com.vtx.vtx_authorization_server.core.ClientAuthenticationMethod;import com.vtx.vtx_authorization_server.core.OAuth2AuthorizationResponseType;import com.vtx.vtx_authorization_server.core.OidcScopes;import com.vtx.vtx_authorization_server.http.converter.HttpMessageConverters;import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;import com.vtx.vtx_security.web.util.matcher.AntPathRequestMatcher;import com.vtx.vtx_security.web.util.matcher.RequestMatcher;import jakarta.servlet.FilterChain;import jakarta.servlet.http.HttpServletRequest;import jakarta.servlet.http.HttpServletResponse;import java.io.IOException;import java.util.List;import java.util.function.Consumer;import org.springframework.boot.web.servlet.filter.OrderedFilter;import org.springframework.core.Ordered;import org.springframework.http.HttpMethod;import org.springframework.http.MediaType;import org.springframework.http.server.ServletServerHttpResponse;import org.springframework.stereotype.Component;import org.springframework.util.Assert;import org.springframework.web.filter.OncePerRequestFilter;import org.springframework.web.util.UriComponentsBuilder;@Componentpublic class AuthorizationServerMetadataEndpointFilter extends OncePerRequestFilter implements OrderedFilter {  private final RequestMatcher matcher = createMatcher();  public static final String DEFAULT_OIDC_PROVIDER_CONFIGURATION_ENDPOINT = "/.well-known/openid-configuration";  private Consumer<OidcProviderConfiguration.Builder> oidcProviderConfigurationBuilderConsumer = builder -> {  };  public void setOidcProviderConfigurationBuilderConsumer(Consumer<OidcProviderConfiguration.Builder> oidcProviderConfigurationBuilderConsumer) {    Assert.notNull(oidcProviderConfigurationBuilderConsumer, "oidcProviderConfigurationBuilderConsumer cannot be null");    this.oidcProviderConfigurationBuilderConsumer = oidcProviderConfigurationBuilderConsumer;  }  @Override  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {    AuthorizationServerContext authorizationServerContext = AuthorizationServerContextHolder.getContext();    AuthorizationServerSettings authorizationServerSettings = authorizationServerContext.getAuthorizationServerSettings();    String issuer = authorizationServerContext.getIssuer();    OidcProviderConfiguration.Builder builder = OidcProviderConfiguration.builder()            .issuer(issuer)            .authorizationEndpoint(asUrl(issuer, authorizationServerSettings.getAuthorizationEndpoint()))            .tokenEndpoint(asUrl(issuer, authorizationServerSettings.getTokenEndpoint()))            .tokenEndpointAuthenticationMethods(clientAuthenticationMethods())            .jwkSetUri(asUrl(issuer, authorizationServerSettings.getJwkSetEndpoint()))            .userInfoEndpoint(asUrl(issuer, authorizationServerSettings.getUserInfoEndpoint()))            .responseType(OAuth2AuthorizationResponseType.CODE.getValue())            .grantTypes(grantTypes -> {              grantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE.getGrantType());              grantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS.getGrantType());              grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN.getGrantType());              grantTypes.add(AuthorizationGrantType.TOKEN_EXCHANGE.getGrantType());              grantTypes.add(AuthorizationGrantType.DEVICE_CODE.getGrantType());              grantTypes.add(AuthorizationGrantType.PASSWORD.getGrantType());            })            .endSessionEndpoint(asUrl(issuer, authorizationServerSettings.getLogoutEndpoint()))            .tokenRevocationEndpoint(asUrl(issuer, authorizationServerSettings.getTokenRevocationEndpoint()))            .tokenRevocationEndpointMethods(clientAuthenticationMethods())            .scope(OidcScopes.OPENID.getScope())            .tokenIntrospectionEndpoint(asUrl(issuer, authorizationServerSettings.getTokenIntrospectionEndpoint()))            .codeChallengeMethod("S256");    oidcProviderConfigurationBuilderConsumer.accept(builder);    ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);    HttpMessageConverters.getJsonMessageConverter().write(builder.build().getClaims(), MediaType.APPLICATION_JSON, servletServerHttpResponse);  }  private RequestMatcher createMatcher() {    return new AntPathRequestMatcher(DEFAULT_OIDC_PROVIDER_CONFIGURATION_ENDPOINT, HttpMethod.GET.name());  }  @Override  public int getOrder() {    return Ordered.LOWEST_PRECEDENCE;  }  private static Consumer<List<String>> clientAuthenticationMethods() {    return authenticationMethods -> {      authenticationMethods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());      authenticationMethods.add(ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue());      authenticationMethods.add(ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue());      authenticationMethods.add(ClientAuthenticationMethod.TLS_CLIENT_AUTH.getValue());      authenticationMethods.add(ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue());    };  }  private static String asUrl(String issuer, String endpoint) {    return UriComponentsBuilder.fromUriString(issuer).path(endpoint).build().toUriString();  }  @Override  protected boolean shouldNotFilter(HttpServletRequest request) {    return !matcher.matches(request);  }}