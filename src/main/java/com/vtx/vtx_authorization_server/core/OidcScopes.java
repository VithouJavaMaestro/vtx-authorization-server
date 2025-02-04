package com.vtx.vtx_authorization_server.core;

import java.util.Objects;

public class OidcScopes {

  public static final OidcScopes OPENID = new OidcScopes("openid");

  public static final OidcScopes PROFILE = new OidcScopes("profile");

  public static final OidcScopes EMAIL = new OidcScopes("email");

  public static final OidcScopes PHONE = new OidcScopes("phone");

  private final String scope;

  public OidcScopes(String scope) {
    this.scope = scope;
  }

  public String getScope() {
    return scope;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OidcScopes that)) return false;
    return Objects.equals(scope, that.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(scope);
  }
}
