package mx.marco.kokonutstudio.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import mx.marco.kokonutstudio.di.annotation.PerActivity
import mx.marco.kokonutstudio.di.module.*
import javax.inject.Singleton

@PerActivity
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    BaseRetrofitModule::class,
    ActivityModule::class,
    FragmentModule::class,
    RepositoryModule::class,
    ApisModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}