package com.vtx.vtx_authorization_server.core;

import com.vtx.vtx_authorization_server.conversion.ClaimConversionService;
import java.net.URL;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;

public interface ClaimAccessor {

  Map<String, Object> getClaims();

  default boolean hasClaim(final String claim) {
    Assert.notNull(claim, "claim must not be null");
    return getClaims().containsKey(claim);
  }

  default String getClaimAsString(final String claim) {
    return (hasClaim(claim) ? ClaimConversionService.getSharedInstance().convert(getClaims().get(claim), String.class) : null);
  }

  default Boolean getClaimAsBoolean(final String claim) {
    if (!hasClaim(claim)) {
      return null;
    } else {
      Object claimValue = this.getClaims().get(claim);
      Boolean convertedValue = ClaimConversionService.getSharedInstance().convert(claimValue, Boolean.class);
      Assert.notNull(claimValue, "Unable to convert claim " + claim + " of type " + claimValue.getClass() + " to Map.");
      return convertedValue;
    }
  }

  default Instant getClaimAsInstant(final String claim) {
    if (!hasClaim(claim)) {
      return null;
    } else {
      Object claimValue = this.getClaims().get(claim);
      Instant convertedValue = ClaimConversionService.getSharedInstance().convert(claimValue, Instant.class);
      Assert.notNull(convertedValue, "Unable to convert claim " + claim + " to Instant.");
      return convertedValue;
    }
  }

  default URL getClaimAsURL(final String claim) {
    if (!hasClaim(claim)) {
      return null;
    }
    Object claimValue = this.getClaims().get(claim);
    URL convertedValue = ClaimConversionService.getSharedInstance().convert(claimValue, URL.class);
    Assert.notNull(convertedValue, "Unable to convert claim " + claim + " to URL.");
    return convertedValue;
  }

  @SuppressWarnings("unchecked")
  default List<String> getClaimAsStringList(final String claim) {
    if (!hasClaim(claim)) {
      return Collections.emptyList();
    }
    TypeDescriptor sourceDescriptor = TypeDescriptor.valueOf(Object.class);
    TypeDescriptor targetDescriptor = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class));
    Object claimValue = this.getClaims().get(claim);
    List<String> convertedValue = (List<String>) ClaimConversionService.getSharedInstance().convert(claimValue, sourceDescriptor, targetDescriptor);
    Assert.notNull(convertedValue, "Unable to convert claim " + claim + " to List.");
    return convertedValue;
  }
}
