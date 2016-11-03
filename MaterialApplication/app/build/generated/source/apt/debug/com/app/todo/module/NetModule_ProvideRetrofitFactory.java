package com.app.todo.module;

import com.app.todo.retrofit.ToStringConverter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final NetModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<ToStringConverter> toStringConverterProvider;

  public NetModule_ProvideRetrofitFactory(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<ToStringConverter> toStringConverterProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
    assert toStringConverterProvider != null;
    this.toStringConverterProvider = toStringConverterProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRetrofit(okHttpClientProvider.get(), toStringConverterProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<ToStringConverter> toStringConverterProvider) {
    return new NetModule_ProvideRetrofitFactory(
        module, okHttpClientProvider, toStringConverterProvider);
  }
}
