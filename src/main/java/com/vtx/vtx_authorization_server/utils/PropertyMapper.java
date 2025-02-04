package com.vtx.vtx_authorization_server.utils;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public final class PropertyMapper {

  private static final Predicate<?> ALWAYS = o -> true;

  private static final PropertyMapper INSTANCE = new PropertyMapper(null, null);

  private final PropertyMapper parent;

  private final SourceOperator sourceOperator;

  public PropertyMapper(PropertyMapper parent, SourceOperator sourceOperator) {
    this.parent = parent;
    this.sourceOperator = sourceOperator;
  }

  public PropertyMapper alwaysApplyWhenNonNull() {
    return alwaysApplying(this::whenNonNull);
  }

  public PropertyMapper alwaysApplying(SourceOperator sourceOperator) {
    return new PropertyMapper(this, sourceOperator);
  }

  public <T> Source<T> whenNonNull(Source<T> source) {
    return source.whenNonNull();
  }

  public <T> Source<T> from(Supplier<T> supplier) {
    Assert.notNull(supplier, "Supplier must not be null");
    Source<T> source = getSource(supplier);
    if (sourceOperator != null) {
      source = this.sourceOperator.apply(source);
    }
    return source;
  }

  public <T> Source<T> from(T value) {
    Assert.notNull(value, "Value must not be null");
    return from(() -> value);
  }

  @SuppressWarnings("unchecked")
  private <T> Source<T> getSource(Supplier<T> supplier) {
    if (this.parent != null) {
      return this.parent.from(supplier);
    }
    return new Source<>(supplier, (Predicate<T>) ALWAYS);
  }

  public static PropertyMapper getInstance() {
    return INSTANCE;
  }

  @FunctionalInterface
  public interface SourceOperator {
    <T> Source<T> apply(Source<T> source);
  }

  public static final class Source<T> {
    private final Supplier<T> supplier;

    private final Predicate<T> predicate;

    public Source(Supplier<T> supplier, Predicate<T> predicate) {
      this.supplier = supplier;
      this.predicate = predicate;
    }

    public <R extends Number> Source<Integer> asInt(Function<T, R> adapter) {
      return as(adapter).as(Number::intValue);
    }

    public <R extends Number> Source<Long> asLong(Function<T, R> adapter) {
      return as(adapter).as(Number::longValue);
    }

    public <R extends Number> Source<String> asString(Function<T, R> adapter) {
      return as(adapter).as(String::valueOf);
    }

    public Source<T> whenHasText() {
      return when(value -> StringUtils.hasText(Objects.toString(value)));
    }

    public Source<T> whenEquals(Object target) {
      return when(value -> Objects.equals(value, target));
    }

    public Source<T> whenNegated(Predicate<T> negated) {
      return when(value -> !negated.test(value));
    }

    public Source<T> whenNonNull() {
      return new Source<>(new NullPointerExceptionSafeSupplier<>(supplier), Objects::nonNull);
    }

    public Source<T> when(Predicate<T> predicate) {
      return new Source<>(supplier, (this.predicate != null ? this.predicate.and(predicate) : predicate));
    }

    public void to(Consumer<T> consumer) {
      Assert.notNull(consumer, () -> "consumer must not be null");
      T value = this.supplier.get();
      if (this.predicate.test(value)) {
        consumer.accept(value);
      }
    }

    public <R> Source<R> as(Function<T, R> adapter) {
      BooleanSupplier testSupplier = () -> this.predicate.test(this.supplier.get());
      Predicate<R> testPredicate = (t) -> testSupplier.getAsBoolean();
      Supplier<R> newSupplier = () -> {
        if (testSupplier.getAsBoolean()) {
          return adapter.apply(this.supplier.get());
        } else {
          return null;
        }
      };
      return new Source<>(newSupplier, testPredicate);
    }
  }

  private record NullPointerExceptionSafeSupplier<T>(Supplier<T> supplier) implements Supplier<T> {

    @Override
    public T get() {
      try {
        return this.supplier.get();
      } catch (NullPointerException e) {
        return null;
      }
    }
  }
}
