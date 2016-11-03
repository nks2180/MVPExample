package com.app.todo.module;

import com.app.todo.retrofit.ApiController;
import com.app.todo.retrofit.ApiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvidesApiControllerFactory implements Factory<ApiController> {
  private final NetModule module;

  private final Provider<ApiService> apiServiceProvider;

  public NetModule_ProvidesApiControllerFactory(
      NetModule module, Provider<ApiService> apiServiceProvider) {
    assert module != null;
    this.module = module;
    assert apiServiceProvider != null;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ApiController get() {
    return Preconditions.checkNotNull(
        module.providesApiController(apiServiceProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ApiController> create(
      NetModule module, Provider<ApiService> apiServiceProvider) {
    return new NetModule_ProvidesApiControllerFactory(module, apiServiceProvider);
  }
}
