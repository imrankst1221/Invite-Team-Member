package imrankst1221.invite.team.member.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import imrankst1221.invite.team.member.data.api.ApiService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideUserApiService(): ApiService {
        return ApiService.create()
    }
}