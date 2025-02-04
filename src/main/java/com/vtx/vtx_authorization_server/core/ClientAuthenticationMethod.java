package com.vtx.vtx_authorization_server.core;

import java.util.Objects;

public class ClientAuthenticationMethod {

  public static final ClientAuthenticationMethod CLIENT_SECRET_BASIC = new ClientAuthenticationMethod("client_secret_basic");

  public static final ClientAuthenticationMethod CLIENT_SECRET_POST = new ClientAuthenticationMethod("client_secret_post");

  public static final ClientAuthenticationMethod CLIENT_SECRET_JWT = new ClientAuthenticationMethod("client_secret_jwt");

  public static final ClientAuthenticationMethod PRIVATE_KEY_JWT = new ClientAuthenticationMethod("private_key_jwt");

  public static final ClientAuthenticationMethod NONE = new ClientAuthenticationMethod("none");

  public static final ClientAuthenticationMethod TLS_CLIENT_AUTH = new ClientAuthenticationMethod("tls_client_auth");


  private final String value;

  public ClientAuthenticationMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ClientAuthenticationMethod that)) return false;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
