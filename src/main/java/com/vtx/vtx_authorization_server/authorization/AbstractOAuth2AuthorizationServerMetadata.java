package com.vtx.vtx_authorization_server.authorization;

import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.springframework.util.Assert;

public abstract class AbstractOAuth2AuthorizationServerMetadata implements OAuth2AuthorizationServerMetadataClaimAccessor {

  private final Map<String, Object> claims;

  protected AbstractOAuth2AuthorizationServerMetadata(Map<String, Object> claims) {
    Assert.notEmpty(claims, "claims must not be empty");
    this.claims = Collections.unmodifiableMap(claims);
  }

  @Override
  public Map<String, Object> getClaims() {
    return claims;
  }

  protected abstract static class AbstractBuilder<T extends AbstractOAuth2AuthorizationServerMetadata, B extends AbstractBuilder<T, B>> {

    private final Map<String, Object> claims = new LinkedHashMap<>();

    protected AbstractBuilder() {
    }

    protected Map<String, Object> getClaims() {
      return claims;
    }

    public B issuer(String issuer) {
      return claim(OAuth2AuthorizationServerMetadataNames.ISSUER, issuer);
    }

    public B authorizationEndpoint(String authorizationEndpoint) {
      return claim(OAuth2AuthorizationServerMetadataNames.AUTHORIZATION_ENDPOINT, authorizationEndpoint);
    }

    public B tokenEndpoint(String tokenEndpoint) {
      return claim(OAuth2AuthorizationServerMetadataNames.TOKEN_ENDPOINT, tokenEndpoint);
    }

    public B tokenEndpointAuthenticationMethod(String tokenEndpointAuthenticationMethod) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.TOKEN_ENDPOINT_METHOD_SUPPORTED, tokenEndpointAuthenticationMethod);
      return getSelf();
    }

    public B tokenEndpointAuthenticationMethods(Consumer<List<String>> tokenEndpointAuthenticationMethods) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.TOKEN_ENDPOINT_METHOD_SUPPORTED, tokenEndpointAuthenticationMethods);
      return getSelf();
    }


    public B jwkSetUri(String jwkSetUri) {
      return claim(OAuth2AuthorizationServerMetadataNames.JWKS_URI, jwkSetUri);
    }

    public B scope(String scope) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.SCOPES_SUPPORTED, scope);
      return getSelf();
    }

    public B codeChallengeMethod(String codeChallengeMethod) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.CODE_CHALLENGE_METHOD_SUPPORTED, codeChallengeMethod);
      return getSelf();
    }

    public B codeChallengeMethods(Consumer<List<String>> codeChallengeMethods) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.CODE_CHALLENGE_METHOD_SUPPORTED, codeChallengeMethods);
      return getSelf();
    }

    public B tokenRevocationEndpoint(String tokenRevocationEndpoint) {
      return claim(OAuth2AuthorizationServerMetadataNames.TOKEN_REVOCATION_ENDPOINT, tokenRevocationEndpoint);
    }

    public B tokenRevocationEndpointMethod(String tokenRevocationEndpointMethod) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.TOKEN_REVOCATION_ENDPOINT_METHOD, tokenRevocationEndpointMethod);
      return getSelf();
    }

    public B tokenRevocationEndpointMethods(Consumer<List<String>> tokenRevocationEndpointMethodsConsumer) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.TOKEN_REVOCATION_ENDPOINT_METHOD, tokenRevocationEndpointMethodsConsumer);
      return getSelf();
    }

    public B scopes(Consumer<List<String>> scopesConsumer) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.SCOPES_SUPPORTED, scopesConsumer);
      return getSelf();
    }

    public B responseType(String responseType) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.RESPONSE_TYPES_SUPPORTED, responseType);
      return getSelf();
    }

    public B responseTypes(Consumer<List<String>> responseTypesConsumer) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.RESPONSE_TYPES_SUPPORTED, responseTypesConsumer);
      return getSelf();
    }

    public B grantType(String grantType) {
      addToClaimList(OAuth2AuthorizationServerMetadataNames.GRANT_TYPES_SUPPORTED, grantType);
      return getSelf();
    }

    public B grantTypes(Consumer<List<String>> grantTypesConsumer) {
      acceptClaimValues(OAuth2AuthorizationServerMetadataNames.GRANT_TYPES_SUPPORTED, grantTypesConsumer);
      return getSelf();
    }

    public B tokenIntrospectionEndpoint(String tokenIntrospectionEndpoint) {
      return claim(OAuth2AuthorizationServerMetadataNames.TOKEN_INTROSPECTION_ENDPOINT, tokenIntrospectionEndpoint);
    }

    private B getSelf() {
      B self = self();
      Assert.notNull(self, "self cannot be null");
      Assert.isInstanceOf(this.getClass(), self, "self is not an instance of " + this.getClass());
      return self;
    }

    public abstract T build();

    protected static void validateURL(Object url, String errorMessage) {
      if (URL.class.isAssignableFrom(url.getClass())) {
        return;
      }
      try {
        new URI(Objects.toString(url)).toURL();
      } catch (Exception exception) {
        throw new IllegalArgumentException(errorMessage, exception);
      }
    }

    @SuppressWarnings("unchecked")
    protected void addToClaimList(String name, String value) {
      Assert.hasText(name, "name must not be empty");
      Assert.notNull(value, "value must not be empty");
      getClaims().computeIfAbsent(name, (k) -> new LinkedList<String>());
      ((List<String>) getClaims().get(name)).add(value);
    }

    @SuppressWarnings("unchecked")
    protected void acceptClaimValues(String name, Consumer<List<String>> valuesConsumer) {
      Assert.hasText(name, "name must not be empty");
      Assert.notNull(valuesConsumer, "valuesConsumer must not be null");
      getClaims().computeIfAbsent(name, (k) -> new LinkedList<String>());
      valuesConsumer.accept((List<String>) getClaims().get(name));
    }

    public B claim(String key, Object value) {
      Assert.hasText(key, "key must not be empty");
      Assert.notNull(value, "value must not be null");
      claims.put(key, value);
      return self();
    }

    protected abstract B self();
  }
}
