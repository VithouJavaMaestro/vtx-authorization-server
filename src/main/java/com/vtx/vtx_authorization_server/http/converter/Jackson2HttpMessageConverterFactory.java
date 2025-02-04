package com.vtx.vtx_authorization_server.http.converter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ClassUtils;

public class Jackson2HttpMessageConverterFactory implements HttpMessageConverterFactory {
  @Override
  public HttpMessageConverter<Object> createHttpMessageConverter(ClassLoader classLoader) {
    if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader)) {
      return new MappingJackson2HttpMessageConverter();
    }
    return null;
  }
}
