package mx.marco.kokonutstudio.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class RemoveErrorTextWatcher(private val textInputLayout: TextInputLayout) : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
        textInputLayout.error = null
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //blank intentionally
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //blank intentionally
    }
}