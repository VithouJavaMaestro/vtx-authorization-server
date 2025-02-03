package com.vtx.vtx_authorization_server.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SourceDelegator<T> implements PropertyConverter.Source<T> {
  private final Supplier<T> delegate;

  public SourceDelegator(Supplier<T> delegate) {
    this.delegate = delegate;
  }

  @Override
  public void to(Consumer<T> to) {
    to.accept(delegate.get());
  }
}
