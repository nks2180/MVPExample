package com.app.todo.activity;

import com.app.todo.presenter.HomePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class HomeActivity_MembersInjector implements MembersInjector<HomeActivity> {
  private final Provider<HomePresenter> homePresenterProvider;

  public HomeActivity_MembersInjector(Provider<HomePresenter> homePresenterProvider) {
    assert homePresenterProvider != null;
    this.homePresenterProvider = homePresenterProvider;
  }

  public static MembersInjector<HomeActivity> create(
      Provider<HomePresenter> homePresenterProvider) {
    return new HomeActivity_MembersInjector(homePresenterProvider);
  }

  @Override
  public void injectMembers(HomeActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.homePresenter = homePresenterProvider.get();
  }

  public static void injectHomePresenter(
      HomeActivity instance, Provider<HomePresenter> homePresenterProvider) {
    instance.homePresenter = homePresenterProvider.get();
  }
}
