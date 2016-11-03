package com.app.todo.presenter;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BasePresenterImpl_Factory<B extends BaseView>
    implements Factory<BasePresenterImpl<B>> {
  private final Provider<Context> baseContextProvider;

  public BasePresenterImpl_Factory(Provider<Context> baseContextProvider) {
    assert baseContextProvider != null;
    this.baseContextProvider = baseContextProvider;
  }

  @Override
  public BasePresenterImpl<B> get() {
    return new BasePresenterImpl<B>(baseContextProvider.get());
  }

  public static <B extends BaseView> Factory<BasePresenterImpl<B>> create(
      Provider<Context> baseContextProvider) {
    return new BasePresenterImpl_Factory<B>(baseContextProvider);
  }
}
