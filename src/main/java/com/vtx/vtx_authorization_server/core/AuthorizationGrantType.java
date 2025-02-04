package com.vtx.vtx_authorization_server.core;

import java.util.Objects;

public class AuthorizationGrantType {

  public static final AuthorizationGrantType AUTHORIZATION_CODE = new AuthorizationGrantType("authorization_code");

  public static final AuthorizationGrantType CLIENT_CREDENTIALS = new AuthorizationGrantType("client_credentials");

  public static final AuthorizationGrantType REFRESH_TOKEN = new AuthorizationGrantType("refresh_token");

  public static final AuthorizationGrantType TOKEN_EXCHANGE = new AuthorizationGrantType("urn:ietf:params:oauth:grant-type:token-exchange");

  public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

  public static final AuthorizationGrantType DEVICE_CODE = new AuthorizationGrantType("device_code");

  private final String grantType;

  public AuthorizationGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getGrantType() {
    return grantType;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AuthorizationGrantType that)) return false;
    return Objects.equals(grantType, that.grantType);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(grantType);
  }
}
