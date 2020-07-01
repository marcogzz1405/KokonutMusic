package mx.marco.kokonutstudio.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import mx.marco.kokonutstudio.di.component.DaggerAppComponent

class KokonutStudioApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}