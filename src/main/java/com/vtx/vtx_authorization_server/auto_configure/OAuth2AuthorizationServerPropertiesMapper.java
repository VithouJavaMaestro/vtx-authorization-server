package com.vtx.vtx_authorization_server.auto_configure;

import com.vtx.vtx_authorization_server.config.OAuth2AuthorizationServerProperties;
import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;
import com.vtx.vtx_authorization_server.utils.PropertyMapper;

final class OAuth2AuthorizationServerPropertiesMapper {
  private final OAuth2AuthorizationServerProperties properties;

  OAuth2AuthorizationServerPropertiesMapper(OAuth2AuthorizationServerProperties properties) {
    this.properties = properties;
  }

  AuthorizationServerSettings asAuthorizationServerSettings() {
    PropertyMapper mapper = PropertyMapper.getInstance().alwaysApplyWhenNonNull();
    AuthorizationServerSettings.Builder authorizationSettingsBuilder = AuthorizationServerSettings.builder();
    OAuth2AuthorizationServerProperties.Endpoint endpoint = properties.getEndpoint();
    OAuth2AuthorizationServerProperties.OidcEndpoint oidc = endpoint.getOidc();
    mapper.from(properties::getIssuer).to(authorizationSettingsBuilder::issuer);
    mapper.from(endpoint::getAuthorizationEndpoint).to(authorizationSettingsBuilder::authorizationEndpoint);
    mapper.from(endpoint::getTokenEndpoint).to(authorizationSettingsBuilder::tokenEndpoint);
    mapper.from(endpoint::getJwkSetEndpoint).to(authorizationSettingsBuilder::jwkSetEndpoint);
    mapper.from(endpoint::getTokenRevocationEndpoint).to(authorizationSettingsBuilder::tokenRevocationEndpoint);
    mapper.from(endpoint::getTokenIntrospectionEndpoint).to(authorizationSettingsBuilder::tokenIntrospectionEndpoint);
    mapper.from(oidc::getLogoutEndpoint).to(authorizationSettingsBuilder::logoutEndpoint);
    mapper.from(oidc::getUserInfoEndpoint).to(authorizationSettingsBuilder::userInfoEndpoint);
    return authorizationSettingsBuilder.build();
  }
}
