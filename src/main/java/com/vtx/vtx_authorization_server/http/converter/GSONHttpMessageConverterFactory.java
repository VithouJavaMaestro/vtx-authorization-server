package com.vtx.vtx_authorization_server.http.converter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.ClassUtils;

public class GSONHttpMessageConverterFactory implements HttpMessageConverterFactory {
  @Override
  public HttpMessageConverter<Object> createHttpMessageConverter(ClassLoader classLoader) {
    if (ClassUtils.isPresent("com.google.gson.Gson", classLoader)) {
      return new GsonHttpMessageConverter();
    }
    return null;
  }
}
