package mx.marco.kokonutstudio.ui.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import mx.marco.kokonutstudio.R

class KokonutProgressDialog(context: Context) : ProgressDialog(context, R.style.LoaderDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_dialog_progress)
    }

}