package com.vtx.vtx_authorization_server.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.Assert;

@ConfigurationProperties(
        prefix = "vtx.security.oauth2.authorization-server.config"
)
public class OAuth2AuthorizationServerProperties implements InitializingBean  {

  private String issuer;

  private final Map<String, ClientConfig> clientConfigs = new HashMap<>();

  private final Endpoint endpoint = new Endpoint();

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public Map<String, ClientConfig> getClientConfigs() {
    return clientConfigs;
  }

  public Endpoint getEndpoint() {
    return endpoint;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
      validateClients();
  }

  private void validateClients() {
    getClientConfigs().values().forEach(clientConfig -> {
      final var clientConfigInformation = clientConfig.getClientConfigInformation();
      Assert.hasText(clientConfigInformation.getClientId(), "clientId must not be empty");
      Assert.hasText(clientConfigInformation.getClientSecret(), "clientSecret must not be empty");
    });
  }

  public static class ClientConfig {
    @NestedConfigurationProperty
    private final ClientConfigInformation clientConfigInformation = new ClientConfigInformation();

    private String jwkSetUri;

    public ClientConfigInformation getClientConfigInformation() {
      return clientConfigInformation;
    }

    public String getJwkSetUri() {
      return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
      this.jwkSetUri = jwkSetUri;
    }
  }

  public static class ClientConfigInformation {

    private String clientId;

    private String clientSecret;

    private String clientName;

    private Set<String> scopes = new HashSet<>();

    private Set<String> redirectUris = new HashSet<>();

    private Set<String> authorizationGrantTypes = new HashSet<>();

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public String getClientSecret() {
      return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
    }

    public String getClientName() {
      return clientName;
    }

    public void setClientName(String clientName) {
      this.clientName = clientName;
    }

    public Set<String> getScopes() {
      return scopes;
    }

    public void setScopes(Set<String> scopes) {
      this.scopes = scopes;
    }

    public Set<String> getRedirectUris() {
      return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
      this.redirectUris = redirectUris;
    }

    public Set<String> getAuthorizationGrantTypes() {
      return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
      this.authorizationGrantTypes = authorizationGrantTypes;
    }
  }

  public static class Endpoint {
    private String authorizationEndpoint = "/oauth2/authorize";

    private String tokenEndpoint = "/oauth2/token";

    private String jwkSetEndpoint = "/oauth2/jwks";

    private String tokenRevocationEndpoint = "/oauth2/revoke";

    private String tokenIntrospectionEndpoint = "/oauth2/introspect";

    @NestedConfigurationProperty
    private OidcEndpoint oidc = new OidcEndpoint();

    public String getAuthorizationEndpoint() {
      return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
      this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getTokenEndpoint() {
      return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
      this.tokenEndpoint = tokenEndpoint;
    }

    public String getJwkSetEndpoint() {
      return jwkSetEndpoint;
    }

    public void setJwkSetEndpoint(String jwkSetEndpoint) {
      this.jwkSetEndpoint = jwkSetEndpoint;
    }

    public OidcEndpoint getOidc() {
      return oidc;
    }

    public void setOidc(OidcEndpoint oidc) {
      this.oidc = oidc;
    }

    public String getTokenRevocationEndpoint() {
      return tokenRevocationEndpoint;
    }

    public void setTokenRevocationEndpoint(String tokenRevocationEndpoint) {
      this.tokenRevocationEndpoint = tokenRevocationEndpoint;
    }

    public String getTokenIntrospectionEndpoint() {
      return tokenIntrospectionEndpoint;
    }

    public void setTokenIntrospectionEndpoint(String tokenIntrospectionEndpoint) {
      this.tokenIntrospectionEndpoint = tokenIntrospectionEndpoint;
    }
  }

  public static class OidcEndpoint {
    private String logoutEndpoint = "/connect/logout";

    private String userInfoEndpoint = "/userinfo";

    public String getLogoutEndpoint() {
      return logoutEndpoint;
    }

    public void setLogoutEndpoint(String logoutEndpoint) {
      this.logoutEndpoint = logoutEndpoint;
    }

    public String getUserInfoEndpoint() {
      return userInfoEndpoint;
    }

    public void setUserInfoEndpoint(String userInfoEndpoint) {
      this.userInfoEndpoint = userInfoEndpoint;
    }
  }
}
