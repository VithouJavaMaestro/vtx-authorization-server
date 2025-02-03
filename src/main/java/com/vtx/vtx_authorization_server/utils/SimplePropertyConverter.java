package com.vtx.vtx_authorization_server.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SimplePropertyConverter implements PropertyConverter {

  @Override
  public <T> Source<T> from(Supplier<T> from) {
    return new SimpleSource<>(from);
  }

  static class SimpleSource<T> implements Source<T> {

    private final Supplier<T> from;

    public SimpleSource(Supplier<T> from) {
      this.from = from;
    }

    @Override
    public void to(Consumer<T> to) {
      to.accept(from.get());
    }
  }
}
