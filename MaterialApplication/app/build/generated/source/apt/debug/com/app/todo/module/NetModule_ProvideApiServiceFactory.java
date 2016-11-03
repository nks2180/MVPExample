package com.app.todo.module;

import com.app.todo.retrofit.ApiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideApiServiceFactory implements Factory<ApiService> {
  private final NetModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetModule_ProvideApiServiceFactory(NetModule module, Provider<Retrofit> retrofitProvider) {
    assert module != null;
    this.module = module;
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiService get() {
    return Preconditions.checkNotNull(
        module.provideApiService(retrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ApiService> create(NetModule module, Provider<Retrofit> retrofitProvider) {
    return new NetModule_ProvideApiServiceFactory(module, retrofitProvider);
  }
}
