package com.vtx.vtx_authorization_server.conversion;

import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToURLConverter implements GenericConverter {
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    return Collections.singleton(new ConvertiblePair(Object.class, URL.class));
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (source != null) {
      if (source instanceof URL value) {
        return value;
      } else {
        try {
          return new URI(Objects.toString(source)).toURL();
        } catch (Exception exception) {
          return null;
        }
      }
    }
    return null;
  }
}
