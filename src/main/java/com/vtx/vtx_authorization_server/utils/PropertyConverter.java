package com.vtx.vtx_authorization_server.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface PropertyConverter {

  <T> Source<T> from(Supplier<T> from);

  interface Source<T> {
    void to(Consumer<T> to);
  }
}
