package mx.marco.kokonutstudio.di.module

import dagger.Module
import dagger.Provides
import mx.marco.kokonutstudio.data.remote.SuggestApi
import mx.marco.kokonutstudio.data.repository.SearchRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAccessRepository(suggestApi: SuggestApi) =
        SearchRepository(suggestApi)
    
}