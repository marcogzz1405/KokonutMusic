package mx.marco.kokonutstudio.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_view_search.view.*
import mx.marco.kokonutstudio.R

class CustomSearchView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    var onSearchListener : OnSearchListener? = null
    private val textWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            onSearchListener?.onQueryTextChanged(p0?.toString()?:"")
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //Blank intentionally
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //Blank intentionally
        }
    }

    init {
        inflate(context, R.layout.custom_view_search, this)

        edtSearch.setOnEditorActionListener { textView, id, _ ->
            return@setOnEditorActionListener when(id) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    onSearchListener?.onQueryTextSubmitted(textView.text.toString())
                    true
                }
                else -> false
            }
        }

        edtSearch.addTextChangedListener(textWatcher)

        btnCancel.setOnClickListener {
            onSearchListener?.onCancelSearch()
        }
    }

    fun requestSearchFocus() {
        edtSearch.callOnClick()
    }

    fun clear() {
        edtSearch.text.clear()
    }

    interface OnSearchListener {
        fun onQueryTextChanged(text : String)
        fun onQueryTextSubmitted(text: String)
        fun onCancelSearch()
    }

}