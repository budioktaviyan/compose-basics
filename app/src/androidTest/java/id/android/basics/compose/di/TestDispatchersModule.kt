@file:Suppress("DEPRECATION")

package id.android.basics.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [DispatchersModule::class]
)
class TestDispatchersModule {

  @Provides
  @DefaultDispatcher
  fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}