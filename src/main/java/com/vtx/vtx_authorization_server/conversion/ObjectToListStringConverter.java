package com.vtx.vtx_authorization_server.conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class ObjectToListStringConverter implements GenericConverter {

  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    Set<ConvertiblePair> convertiblePairs = new HashSet<>(2);
    convertiblePairs.add(new ConvertiblePair(Object.class, List.class));
    convertiblePairs.add(new ConvertiblePair(Object.class, Collection.class));
    return convertiblePairs;
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (source == null) {
      return null;
    } else if (source instanceof Collection<?> values) {
      Collection<String> results = new ArrayList<>(values.size());
      values.forEach(value -> results.add(Objects.toString(value)));
      return results;
    } else {
      return Collections.singletonList(Objects.toString(source));
    }
  }
}
