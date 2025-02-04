package com.vtx.vtx_authorization_server.authorization.oidc;

import com.vtx.vtx_authorization_server.authorization.AbstractOAuth2AuthorizationServerMetadata;
import com.vtx.vtx_authorization_server.authorization.OAuth2AuthorizationServerMetadataNames;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OidcProviderConfiguration extends AbstractOAuth2AuthorizationServerMetadata {

  private OidcProviderConfiguration(Map<String, Object> claims) {
    super(claims);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractBuilder<OidcProviderConfiguration, Builder> {

    private Builder() {
    }

    public Builder endSessionEndpoint(String logoutEndpoint) {
      return claim(OidcProviderMetadataClaimNames.END_SESSION_ENDPOINT, logoutEndpoint);
    }

    public Builder userInfoEndpoint(String userInfoEndpoint) {
      return claim(OidcProviderMetadataClaimNames.USER_INFO_ENDPOINT, userInfoEndpoint);
    }

    public Builder subjectType(String subjectType) {
      addToClaimList(OidcProviderMetadataClaimNames.SUBJECT_TYPES_SUPPORTED, subjectType);
      return self();
    }

    public Builder subjectTypes(Consumer<List<String>> subjectTypesConsumer) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.RESPONSE_TYPES_SUPPORTED, subjectTypesConsumer);
      return self();
    }

    @Override
    public OidcProviderConfiguration build() {
      return new OidcProviderConfiguration(getClaims());
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
