package com.vtx.vtx_authorization_server.conversion;

import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.GenericConversionService;

public class ClaimConversionService extends GenericConversionService {

  private static volatile ClaimConversionService sharedInstance;

  private ClaimConversionService() {
    addConverters(this);
  }

  public static ClaimConversionService getSharedInstance() {
    ClaimConversionService sharedInstance = ClaimConversionService.sharedInstance;
    if (sharedInstance == null) {
      synchronized (ClaimConversionService.class) {
        sharedInstance = ClaimConversionService.sharedInstance;
        if (sharedInstance == null) {
          sharedInstance = new ClaimConversionService();
          ClaimConversionService.sharedInstance = sharedInstance;
        }
      }
    }
    return sharedInstance;
  }


  public static void addConverters(ConverterRegistry converterRegistry) {
    converterRegistry.addConverter(new ObjectToStringConverter());
    converterRegistry.addConverter(new ObjectToBooleanConverter());
    converterRegistry.addConverter(new ObjectToInstantConverter());
    converterRegistry.addConverter(new ObjectToURLConverter());
    converterRegistry.addConverter(new ObjectToListStringConverter());
    converterRegistry.addConverter(new ObjectToMapStringConverter());
  }
}
