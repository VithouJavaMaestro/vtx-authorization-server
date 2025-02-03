package com.vtx.vtx_authorization_server;

import com.vtx.vtx_authorization_server.config.OAuth2AuthorizationServerProperties;
import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;
import org.springframework.boot.context.properties.PropertyMapper;

final class OAuth2AuthorizationServerPropertiesMapper {
  private final OAuth2AuthorizationServerProperties properties;

  OAuth2AuthorizationServerPropertiesMapper(OAuth2AuthorizationServerProperties properties) {
    this.properties = properties;
  }

  AuthorizationServerSettings asAuthorizationServerSettings() {
    PropertyMapper propertyMapper
  }
}
