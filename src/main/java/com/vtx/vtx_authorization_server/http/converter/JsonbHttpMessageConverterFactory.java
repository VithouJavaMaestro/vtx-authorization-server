package com.vtx.vtx_authorization_server.http.converter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.util.ClassUtils;

public class JsonbHttpMessageConverterFactory implements HttpMessageConverterFactory {
  @Override
  public HttpMessageConverter<Object> createHttpMessageConverter(ClassLoader classLoader) {
    if (ClassUtils.isPresent("jakarta.json.bind.Jsonb", classLoader)) {
      return new JsonbHttpMessageConverter();
    }
    return null;
  }
}
