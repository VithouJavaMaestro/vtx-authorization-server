package com.vtx.vtx_authorization_server.settings;

import java.util.Map;

public class AuthorizationServerSettings extends AbstractSettings {

  public AuthorizationServerSettings(Map<String, Object> settings) {
    super(settings);
  }

  public String getIssuer() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.ISSUER);
  }

  public String getAuthorizationEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.AUTHORIZATION_ENDPOINT);
  }

  public String getTokenEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.TOKEN_ENDPOINT);
  }

  public String getUserInfoEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.USER_INFO_ENDPOINT);
  }

  public String getLogoutEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.LOGOUT_ENDPOINT);
  }

  public String getJwkSetEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.JWK_SET_ENDPOINT);
  }

  public String getTokenRevocationEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.TOKEN_REVOCATION_ENDPOINT);
  }

  public String getTokenIntrospectionEndpoint() {
    return getSetting(ConfigurationSettingNames.AuthorizationServer.TOKEN_INTROSPECTION_ENDPOINT);
  }

  public static Builder builder() {
    return new Builder().authorizationEndpoint("/oauth2/authorize")
            .tokenEndpoint("/oauth2/token")
            .userInfoEndpoint("/oauth2/userinfo")
            .logoutEndpoint("/oauth2/logout");
  }

  public static final class Builder extends AbstractSettingsBuilder<AuthorizationServerSettings, Builder> {

    private Builder() {}

    public Builder issuer(String issuer) {
      return setting(ConfigurationSettingNames.AuthorizationServer.ISSUER, issuer);
    }

    public Builder authorizationEndpoint(String authorizationEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.AUTHORIZATION_ENDPOINT, authorizationEndpoint);
    }

    public Builder tokenEndpoint(String tokenEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.TOKEN_ENDPOINT, tokenEndpoint);
    }

    public Builder userInfoEndpoint(String userInfoEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.USER_INFO_ENDPOINT, userInfoEndpoint);
    }

    public Builder logoutEndpoint(String logoutEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.LOGOUT_ENDPOINT, logoutEndpoint);
    }

    public Builder jwkSetEndpoint(String jwkSetEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.JWK_SET_ENDPOINT, jwkSetEndpoint);
    }

    public Builder tokenRevocationEndpoint(String tokenRevocationEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.TOKEN_REVOCATION_ENDPOINT, tokenRevocationEndpoint);
    }

    public Builder tokenIntrospectionEndpoint(String tokenIntrospectionEndpoint) {
      return setting(ConfigurationSettingNames.AuthorizationServer.TOKEN_INTROSPECTION_ENDPOINT, tokenIntrospectionEndpoint);
    }

    @Override
    public AuthorizationServerSettings build() {
      return new AuthorizationServerSettings(getSettings());
    }
  }
}
