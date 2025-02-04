package com.vtx.vtx_authorization_server.settings;

public final class ConfigurationSettingNames {

  private ConfigurationSettingNames() {}

  public static final String SETTINGS_NAMESPACE = "settings.";

  public static final class AuthorizationServer {

    private AuthorizationServer() {}

    public static final String AUTHORIZATION_SERVER_SETTINGS_NAMESPACE = SETTINGS_NAMESPACE + "authorization-server.";

    public static final String ISSUER = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("issuer");

    public static final String AUTHORIZATION_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("authorization-endpoint");

    public static final String TOKEN_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("token-endpoint");

    public static final String USER_INFO_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("user-info-endpoint");

    public static final String LOGOUT_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("logout-endpoint");

    public static final String JWK_SET_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("jwk-set-endpoint");

    public static final String TOKEN_REVOCATION_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("token-revocation-endpoint");

    public static final String TOKEN_INTROSPECTION_ENDPOINT = AUTHORIZATION_SERVER_SETTINGS_NAMESPACE.concat("token-introspection-endpoint");
  }
}
