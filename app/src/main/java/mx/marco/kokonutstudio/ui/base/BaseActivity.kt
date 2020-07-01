package mx.marco.kokonutstudio.ui.base

import android.app.AlertDialog
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import mx.marco.kokonutstudio.R
import mx.marco.kokonutstudio.ui.fragment.KokonutProgressDialog
import mx.marco.kokonutstudio.utils.*

open class BaseActivity : DaggerAppCompatActivity() {

    private var loader: KokonutProgressDialog?  = null

    fun showProgress() {
        if(loader?.isShowing==false)
            loader?.show()
    }

    fun hideProgress() {
        if(loader?.isShowing==true)
            loader?.dismiss()
    }

    fun showErrorMessage(message : String?) {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog).setMessage(message).setPositiveButton("Aceptar"){ dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        builder.create().show()
    }

    fun showMessage(message : String?) {
        val builder = AlertDialog.Builder(this).setMessage(message).setPositiveButton("Aceptar"){ dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        builder.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loader = KokonutProgressDialog(this)
    }

}