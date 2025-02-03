package com.vtx.vtx_authorization_server.utils;

import java.util.function.Supplier;
import org.springframework.util.Assert;

public class ConditionalPropertyConverter implements PropertyConverter {

  private final Supplier<Boolean> supplier;
  private final PropertyConverter parent;

  public ConditionalPropertyConverter(Supplier<Boolean> supplier, PropertyConverter parent) {
    this.parent = parent;
    Assert.notNull(supplier, "Supplier must not be null");
    this.supplier = supplier;
  }

  @Override
  public <T> Source<T> from(Supplier<T> from) {
    if (Boolean.TRUE == supplier.get()) {
      if (parent != null) {
        return parent.from(from);
      } else {
        return new SimplePropertyConverter.SimpleSource<>(from);
      }
    }
    return null;
  }
}
