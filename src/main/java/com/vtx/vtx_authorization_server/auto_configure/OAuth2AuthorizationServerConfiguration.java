package com.vtx.vtx_authorization_server.auto_configure;

import com.vtx.vtx_authorization_server.config.OAuth2AuthorizationServerProperties;
import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OAuth2AuthorizationServerProperties.class)
public class OAuth2AuthorizationServerConfiguration {

  private final OAuth2AuthorizationServerPropertiesMapper propertiesMapper;

  public OAuth2AuthorizationServerConfiguration(OAuth2AuthorizationServerProperties properties) {
    this.propertiesMapper = new OAuth2AuthorizationServerPropertiesMapper(properties);
  }

  @Bean
  @ConditionalOnMissingBean
  AuthorizationServerSettings authorizationServerSettings() {
    return this.propertiesMapper.asAuthorizationServerSettings();
  }
}
