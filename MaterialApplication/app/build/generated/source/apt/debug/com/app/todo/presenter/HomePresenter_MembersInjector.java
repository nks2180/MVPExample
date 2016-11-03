package com.app.todo.presenter;

import com.app.todo.executor.ParsingExecutor;
import com.app.todo.retrofit.ApiController;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class HomePresenter_MembersInjector implements MembersInjector<HomePresenter> {
  private final Provider<ApiController> apiControllerProvider;

  private final Provider<ParsingExecutor> parsingExecutorProvider;

  public HomePresenter_MembersInjector(
      Provider<ApiController> apiControllerProvider,
      Provider<ParsingExecutor> parsingExecutorProvider) {
    assert apiControllerProvider != null;
    this.apiControllerProvider = apiControllerProvider;
    assert parsingExecutorProvider != null;
    this.parsingExecutorProvider = parsingExecutorProvider;
  }

  public static MembersInjector<HomePresenter> create(
      Provider<ApiController> apiControllerProvider,
      Provider<ParsingExecutor> parsingExecutorProvider) {
    return new HomePresenter_MembersInjector(apiControllerProvider, parsingExecutorProvider);
  }

  @Override
  public void injectMembers(HomePresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.apiController = apiControllerProvider.get();
    instance.parsingExecutor = parsingExecutorProvider.get();
  }

  public static void injectApiController(
      HomePresenter instance, Provider<ApiController> apiControllerProvider) {
    instance.apiController = apiControllerProvider.get();
  }

  public static void injectParsingExecutor(
      HomePresenter instance, Provider<ParsingExecutor> parsingExecutorProvider) {
    instance.parsingExecutor = parsingExecutorProvider.get();
  }
}
