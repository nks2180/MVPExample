package com.app.todo.presenter;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class HomePresenter_Factory implements Factory<HomePresenter> {
  private final MembersInjector<HomePresenter> homePresenterMembersInjector;

  private final Provider<Context> baseContextProvider;

  public HomePresenter_Factory(
      MembersInjector<HomePresenter> homePresenterMembersInjector,
      Provider<Context> baseContextProvider) {
    assert homePresenterMembersInjector != null;
    this.homePresenterMembersInjector = homePresenterMembersInjector;
    assert baseContextProvider != null;
    this.baseContextProvider = baseContextProvider;
  }

  @Override
  public HomePresenter get() {
    return MembersInjectors.injectMembers(
        homePresenterMembersInjector, new HomePresenter(baseContextProvider.get()));
  }

  public static Factory<HomePresenter> create(
      MembersInjector<HomePresenter> homePresenterMembersInjector,
      Provider<Context> baseContextProvider) {
    return new HomePresenter_Factory(homePresenterMembersInjector, baseContextProvider);
  }
}
