package com.vtx.vtx_authorization_server.context;

public final class AuthorizationServerContextHolder {
  private static final ThreadLocal<AuthorizationServerContext> holder = new ThreadLocal<>();

  public static AuthorizationServerContext getContext() {
    return holder.get();
  }

  public static void setContext(AuthorizationServerContext authorizationServerContext) {
    if (authorizationServerContext == null) {
      removeContext();
    } else {
      holder.set(authorizationServerContext);
    }
  }

  public static void removeContext() {
    holder.remove();
  }
}
