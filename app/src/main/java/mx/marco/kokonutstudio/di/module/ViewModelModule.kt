package mx.marco.kokonutstudio.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import mx.marco.kokonutstudio.di.annotation.ViewModelKey
import mx.marco.kokonutstudio.ui.viewmodel.DetailsViewModel
import mx.marco.kokonutstudio.ui.viewmodel.MainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel) : ViewModel

}