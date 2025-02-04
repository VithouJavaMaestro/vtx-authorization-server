package com.vtx.vtx_authorization_server.authorization;

import com.vtx.vtx_authorization_server.core.ClaimAccessor;
import java.net.URL;
import java.util.List;

public interface OAuth2AuthorizationServerMetadataClaimAccessor extends ClaimAccessor {
  default URL getIssuer() {
    return getClaimAsURL(OAuth2AuthorizationServerMetadataNames.ISSUER);
  }

  default URL getAuthorizationEndpoint() {
    return getClaimAsURL(OAuth2AuthorizationServerMetadataNames.AUTHORIZATION_ENDPOINT);
  }

  default URL getTokenEndpoint() {
    return getClaimAsURL(OAuth2AuthorizationServerMetadataNames.TOKEN_ENDPOINT);
  }

  default URL getJwksUri() {
    return getClaimAsURL(OAuth2AuthorizationServerMetadataNames.JWKS_URI);
  }

  default List<String> getScopes() {
    return getClaimAsStringList(OAuth2AuthorizationServerMetadataNames.SCOPES_SUPPORTED);
  }

  default List<String> getResponseTypes() {
    return getClaimAsStringList(OAuth2AuthorizationServerMetadataNames.RESPONSE_TYPES_SUPPORTED);
  }

  default List<String> getGrantTypes() {
    return getClaimAsStringList(OAuth2AuthorizationServerMetadataNames.GRANT_TYPES_SUPPORTED);
  }
}
