package com.app.todo.module;

import com.app.todo.executor.ParsingExecutor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvidesParsingExecutorFactory
    implements Factory<ParsingExecutor> {
  private final ApplicationModule module;

  public ApplicationModule_ProvidesParsingExecutorFactory(ApplicationModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ParsingExecutor get() {
    return Preconditions.checkNotNull(
        module.providesParsingExecutor(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ParsingExecutor> create(ApplicationModule module) {
    return new ApplicationModule_ProvidesParsingExecutorFactory(module);
  }
}
