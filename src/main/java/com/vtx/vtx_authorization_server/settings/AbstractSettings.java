package com.vtx.vtx_authorization_server.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.util.Assert;

public abstract class AbstractSettings {

  private final Map<String, Object> settings;

  protected AbstractSettings(Map<String, Object> settings) {
    Assert.notEmpty(settings, "settings must not be empty");
    this.settings = Collections.unmodifiableMap(settings);
  }

  @SuppressWarnings("unchecked")
  public <T> T getSetting(String key) {
    Assert.hasText(key, "key must not be empty");
    return (T) settings.get(key);
  }

  public Map<String, Object> getSettings() {
    return this.settings;
  }

  protected abstract static class AbstractSettingsBuilder<T extends AbstractSettings, B extends AbstractSettingsBuilder<T, B>> {

    private final Map<String, Object> settings = new HashMap<>();

    public B setting(String name, Object value) {
      Assert.hasText(name, "name must not be empty");
      Assert.notNull(value, "value must not be null");
      settings.put(name, value);
      return this.getThis();
    }

    public B settings(Consumer<Map<String, Object>> settingsConsumer) {
      settingsConsumer.accept(this.settings);
      return this.getThis();
    }

    public abstract T build();

    protected final Map<String, Object> getSettings() {
      return this.settings;
    }

    @SuppressWarnings("unchecked")
    public B getThis() {
      return (B) this;
    }
  }
}
