package com.app.todo.module;

import com.app.todo.retrofit.ToStringConverter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideConverterFactory implements Factory<ToStringConverter> {
  private final NetModule module;

  public NetModule_ProvideConverterFactory(NetModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ToStringConverter get() {
    return Preconditions.checkNotNull(
        module.provideConverter(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ToStringConverter> create(NetModule module) {
    return new NetModule_ProvideConverterFactory(module);
  }
}
