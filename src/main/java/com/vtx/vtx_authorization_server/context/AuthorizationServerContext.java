package com.vtx.vtx_authorization_server.context;

import com.vtx.vtx_authorization_server.settings.AuthorizationServerSettings;

public interface AuthorizationServerContext {
  String getIssuer();

  AuthorizationServerSettings getAuthorizationServerSettings();
}
