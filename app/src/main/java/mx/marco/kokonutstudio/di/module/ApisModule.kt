package mx.marco.kokonutstudio.di.module

import dagger.Module
import dagger.Provides
import mx.marco.kokonutstudio.data.remote.SuggestApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApisModule {

    @Provides
    @Singleton
    fun providesAccessApi(client: Retrofit) : SuggestApi {
        return client.create(SuggestApi::class.java)
    }

}