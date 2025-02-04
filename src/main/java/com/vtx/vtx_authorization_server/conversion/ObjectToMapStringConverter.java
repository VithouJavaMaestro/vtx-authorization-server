package com.vtx.vtx_authorization_server.conversion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToMapStringConverter implements GenericConverter {

  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
   return Collections.singleton(new ConvertiblePair(Object.class, Map.class));
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (source instanceof Map<?, ?> valueMap) {
      Map<String, Object> results = new HashMap<>(valueMap.size());
      valueMap.forEach((key, value) -> {
        results.put(Objects.toString(key), value);
      });
      return results;
    }
    return null;
  }
}
