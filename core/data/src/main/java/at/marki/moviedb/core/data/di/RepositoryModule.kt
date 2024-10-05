package at.marki.moviedb.core.data.di

import at.marki.moviedb.core.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsUserLocalRepository(
        userRepository: UserRepository,
    ): UserRepository
}
