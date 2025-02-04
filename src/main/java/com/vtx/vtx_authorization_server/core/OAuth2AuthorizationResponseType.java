package com.vtx.vtx_authorization_server.core;

import java.util.Objects;
import org.springframework.util.Assert;

public class OAuth2AuthorizationResponseType {

  public static final OAuth2AuthorizationResponseType CODE = new OAuth2AuthorizationResponseType("code");

  private final String value;

  public OAuth2AuthorizationResponseType(String value) {
    Assert.hasText(value, "value must not be null or empty");
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof OAuth2AuthorizationResponseType that)) return false;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
