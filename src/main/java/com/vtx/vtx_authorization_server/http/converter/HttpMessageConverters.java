package com.vtx.vtx_authorization_server.http.converter;

import org.springframework.http.converter.HttpMessageConverter;

public class HttpMessageConverters {

  private static final DelegatingHttpMessageConverterFactory factories = new DelegatingHttpMessageConverterFactory();

  static {
    factories.add(new Jackson2HttpMessageConverterFactory());
    factories.add(new GSONHttpMessageConverterFactory());
    factories.add(new JsonbHttpMessageConverterFactory());
  }

  public static HttpMessageConverter<Object> getJsonMessageConverter() {
    return factories.createHttpMessageConverter(HttpMessageConverters.class.getClassLoader());
  }
}
