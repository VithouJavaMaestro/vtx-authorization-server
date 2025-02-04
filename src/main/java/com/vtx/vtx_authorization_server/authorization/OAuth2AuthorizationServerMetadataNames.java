package com.vtx.vtx_authorization_server.authorization;

public final class OAuth2AuthorizationServerMetadataNames {

  private OAuth2AuthorizationServerMetadataNames() {
  }

  public static final String ISSUER = "issuer";

  public static final String AUTHORIZATION_ENDPOINT = "authorization_endpoint";

  public static final String TOKEN_ENDPOINT = "token_endpoint";

  public static final String JWKS_URI = "jwks_uri";

  public static final String SCOPES_SUPPORTED = "scopes_supported";

  public static final String GRANT_TYPES_SUPPORTED = "grant_types_supported";

  public static final String RESPONSE_TYPES_SUPPORTED = "response_types_supported";

  public static final String TOKEN_REVOCATION_ENDPOINT = "revocation_endpoint";

  public static final String TOKEN_REVOCATION_ENDPOINT_METHOD = "revocation_endpoint_method";

  public static final String TOKEN_ENDPOINT_METHOD_SUPPORTED = "token_endpoint_methods_supported";

  public static final String CODE_CHALLENGE_METHOD_SUPPORTED = "code_challenge_methods_supported";

  public static final String TOKEN_INTROSPECTION_ENDPOINT = "introspection_endpoint";
}
