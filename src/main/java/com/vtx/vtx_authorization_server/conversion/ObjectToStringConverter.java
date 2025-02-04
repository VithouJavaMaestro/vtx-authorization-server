package com.vtx.vtx_authorization_server.conversion;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToStringConverter implements GenericConverter {
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, String.class));
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    return Objects.toString(source, null);
  }
}
