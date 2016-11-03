package com.app.todo.module;

import com.app.todo.DBAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DatabaseModule_ProvideDBHelperFactory
    implements Factory<DBAdapter.DatabaseHelper> {
  private final DatabaseModule module;

  public DatabaseModule_ProvideDBHelperFactory(DatabaseModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public DBAdapter.DatabaseHelper get() {
    return Preconditions.checkNotNull(
        module.provideDBHelper(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<DBAdapter.DatabaseHelper> create(DatabaseModule module) {
    return new DatabaseModule_ProvideDBHelperFactory(module);
  }
}
