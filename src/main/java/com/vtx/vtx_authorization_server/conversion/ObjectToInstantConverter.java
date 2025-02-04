package com.vtx.vtx_authorization_server.conversion;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToInstantConverter implements GenericConverter {
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    return Collections.singleton(new ConvertiblePair(Object.class, Instant.class));
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (source != null) {
      if (source instanceof Instant) {
        return source;
      } else if (source instanceof Date value) {
        return value.toInstant();
      } else if (source instanceof Number value) {
        return Instant.ofEpochSecond(value.longValue());
      } else {
        try {
          return Instant.parse(Objects.toString(source));
        } catch (Exception exception) {
          return null;
        }
      }
    }
    return null;
  }
}
