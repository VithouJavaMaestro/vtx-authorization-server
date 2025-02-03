package com.vtx.vtx_authorization_server.utils;

import java.util.function.Supplier;

public enum PropertyConversions {
  ;
  public static PropertyConverter applyWhenNonNullProperty() {
    return new ApplyNonNullPropertyConverter();
  }

  public static PropertyConverter applyConditionalProperty(Supplier<Boolean> booleanSupplier, PropertyConverter propertyConverter) {
    return new ConditionalPropertyConverter(booleanSupplier, propertyConverter);
  }
}
