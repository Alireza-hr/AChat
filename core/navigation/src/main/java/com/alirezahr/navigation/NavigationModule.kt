package com.alirezahr.navigation



@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {
    @Binds
    @Singleton
    abstract fun provideComposeNavigator(aChatComposeNavigator: AChatComposeNavigator)
            : AppComposeNavigator
}