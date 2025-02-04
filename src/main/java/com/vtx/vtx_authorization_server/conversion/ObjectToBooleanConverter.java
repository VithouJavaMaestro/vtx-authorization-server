package com.vtx.vtx_authorization_server.conversion;

import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToBooleanConverter implements GenericConverter {
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    return Collections.singleton(new ConvertiblePair(Object.class, Boolean.class));
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (source != null) {
      if (source instanceof String value) {
        return Boolean.valueOf(value);
      } else if (source instanceof Boolean) {
        return source;
      }
    }
    return null;
  }
}
