package com.vtx.vtx_authorization_server.http.converter;

import org.springframework.http.converter.HttpMessageConverter;

public interface HttpMessageConverterFactory {
  HttpMessageConverter<Object> createHttpMessageConverter(ClassLoader classLoader);
}
