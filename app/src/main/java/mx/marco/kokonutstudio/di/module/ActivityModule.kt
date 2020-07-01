package mx.marco.kokonutstudio.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mx.marco.kokonutstudio.ui.activity.DetailsActivity
import mx.marco.kokonutstudio.ui.activity.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailsActivity() : DetailsActivity

}