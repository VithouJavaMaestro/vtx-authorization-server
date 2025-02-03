package com.vtx.vtx_authorization_server.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

class ApplyNonNullPropertyConverter implements PropertyConverter {

  @Override
  public <T> Source<T> from(Supplier<T> from) {
    return new ExcludeNonNullPropertySource<>(from);
  }

  static class ExcludeNonNullPropertySource<T> implements Source<T> {
    private final Supplier<T> source;

    public ExcludeNonNullPropertySource(Supplier<T> source) {
      this.source = source;
    }

    @Override
    public void to(Consumer<T> to) {
      T sourceProperty;
      try {
        sourceProperty = source.get();
      } catch (NullPointerException exception) {
        sourceProperty = null;
      }
      if (sourceProperty != null) {
        to.accept(sourceProperty);
      }
    }
  }
}
