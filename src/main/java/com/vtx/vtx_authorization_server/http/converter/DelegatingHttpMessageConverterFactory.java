package com.vtx.vtx_authorization_server.http.converter;

import java.util.LinkedList;
import java.util.List;
import org.springframework.http.converter.HttpMessageConverter;

public class DelegatingHttpMessageConverterFactory implements HttpMessageConverterFactory {

  private final List<HttpMessageConverterFactory> delegates = new LinkedList<>();

  public void add(HttpMessageConverterFactory factory) {
    delegates.add(factory);
  }

  @Override
  public HttpMessageConverter<Object> createHttpMessageConverter(ClassLoader classLoader) {
    for (HttpMessageConverterFactory factory : delegates) {
      HttpMessageConverter<Object> httpMessageConverter = factory.createHttpMessageConverter(classLoader);
      if (httpMessageConverter != null) {
        return httpMessageConverter;
      }
    }
    return null;
  }
}
