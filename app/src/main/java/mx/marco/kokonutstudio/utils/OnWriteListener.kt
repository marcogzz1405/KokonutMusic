package mx.marco.kokonutstudio.utils

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

class OnWriteListener(var editable: Editable? = null, val action: (Editable?) -> Unit) : TextWatcher {

    private val handler = Handler()
    private val runnable = Runnable {
        action(editable)
    }

    override fun afterTextChanged(p0: Editable?) {
        editable = p0
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 1500)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //blank intentionally
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //blank intentionally
    }
}