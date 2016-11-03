package com.app.todo.module;

import com.app.todo.DBAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DatabaseModule_ProvidesDBAdapterFactory implements Factory<DBAdapter> {
  private final DatabaseModule module;

  private final Provider<DBAdapter.DatabaseHelper> databaseHelperProvider;

  public DatabaseModule_ProvidesDBAdapterFactory(
      DatabaseModule module, Provider<DBAdapter.DatabaseHelper> databaseHelperProvider) {
    assert module != null;
    this.module = module;
    assert databaseHelperProvider != null;
    this.databaseHelperProvider = databaseHelperProvider;
  }

  @Override
  public DBAdapter get() {
    return Preconditions.checkNotNull(
        module.providesDBAdapter(databaseHelperProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<DBAdapter> create(
      DatabaseModule module, Provider<DBAdapter.DatabaseHelper> databaseHelperProvider) {
    return new DatabaseModule_ProvidesDBAdapterFactory(module, databaseHelperProvider);
  }
}
