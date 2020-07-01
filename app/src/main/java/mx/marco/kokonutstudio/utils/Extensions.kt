package mx.marco.kokonutstudio.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import mx.marco.kokonutstudio.BuildConfig
import mx.marco.kokonutstudio.R
import mx.marco.tiendasneto.data.config.BaseConfig
import java.text.NumberFormat
import java.util.*

fun <T : Activity> Activity.goToActivity(activity: Class<T>, extras : Bundle? = null, flags : Int? = null, requestCode : Int? = null) {
    val intent = Intent(this, activity)
    with(intent) {
        extras?.let {
            putExtras(it)
        }
        flags?.let {
            this.flags = flags
        }
    }
    if(requestCode==null) {
        startActivity(intent)
    } else {
        startActivityForResult(intent,requestCode)
    }
}

fun <T : Activity> Fragment.goToActivity(activity: Class<T>, extras : Bundle? = null, flags : Int? = null, requestCode : Int? = null) {
    val intent = Intent(this.activity, activity)
    with(intent) {
        extras?.let {
            putExtras(it)
        }
        flags?.let {
            this.flags = flags
        }
    }
    if(requestCode==null) {
        startActivity(intent)
    } else {
        startActivityForResult(intent,requestCode)
    }
}

fun LinearLayout.addIndicator(index: Int, @DrawableRes resIndicator : Int = R.drawable.indicator_page_noselected, @DimenRes indicatorRadius : Int = R.dimen.indicatorRadius, @DimenRes indicatorSeparation: Int = R.dimen.indicator_separation){
    val image = ImageView(this.context)
    image.tag = index
    image.setImageResource(resIndicator)
    val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(context.resources.getDimensionPixelSize(indicatorRadius),context.resources.getDimensionPixelSize(
        indicatorRadius
    ))
    layoutParams.marginEnd = context.resources.getDimensionPixelSize(indicatorSeparation)
    layoutParams.marginStart = context.resources.getDimensionPixelSize(indicatorSeparation)
    image.layoutParams = layoutParams
    addView(image)

}

fun LinearLayout.selectIndicator(index: Int,selected: Boolean, resSelectedIndicator : Int = R.drawable.indicator_page_selected, resNoSelectedIndicator : Int = R.drawable.indicator_page_noselected){
    val image = findViewWithTag<ImageView>(index)
    if(selected){
        image?.setImageResource(resSelectedIndicator)
    }else{
        image?.setImageResource(resNoSelectedIndicator)
    }
}

fun Context.savePreference(key: String, value: String?) {
    val sharedPref = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putString(key, value)
        apply()
    }
}

fun Context.savePreference(key: String, value: Int?) {
    val sharedPref = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putInt(key, value?:0)
        apply()
    }
}

fun Context.savePreference(key: String, value: Boolean?) {
    val sharedPref = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putBoolean(key, value?:false)
        apply()
    }
}

fun Context.getIntPreference(key: String) : Int {
    val sharedPreferences = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE)
    return sharedPreferences.getInt(key, 0)
}

fun Context.getStringPreference(key: String) : String? {
    val sharedPreferences = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}

fun Context.getBooleanPreference(key: String) : Boolean {
    val sharedPreferences = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(key, false)
}

fun Context.existsPreference(key: String) : Boolean {
    val sharedPreferences = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE)
    return sharedPreferences.contains(key)
}

fun Context.removePreference(key: String) {
    val sharedPreferences = getSharedPreferences(BaseConfig.PREFERENCE_LABEL,Context.MODE_PRIVATE)
    sharedPreferences.edit().remove(key).apply()
}

fun Context.getStatusBarHeight(): Float {
    var result = 0f
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimension(resourceId)
    }
    return result
}

fun Activity.getWidth(margin : Boolean = true) : Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels + if(margin) resources.getDimensionPixelSize(R.dimen.margin) else 0
}

fun FragmentActivity.getTopFragmentTag() : String? {
    return if(supportFragmentManager.backStackEntryCount<=0) {
        null
    } else {
        supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount-1).name
    }
}

fun Double.formatPrice() : String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    numberFormat.currency = Currency.getInstance("MXN")
    return numberFormat.format(this).replace("MXN","$")
}

fun String.parsePrice() : Double {
    val value = replace(".","").replace(",","").replace("$","")
    return value.toDouble()/100
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0);
    }
}

fun View.getBounds(margin: Int) : Rect {
    val location = IntArray(2)
    getLocationInWindow(location)
    return Rect(
        location[0] - margin,
        location[1] - margin,
        location[0] + measuredWidth + margin,
        location[1] + measuredHeight + margin
    )
}

fun String.toSlug() = toLowerCase()
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")

fun ClosedFloatingPointRange<Float>.acotate(float: Float) = if(float in this){
    float
} else {
    if(float<this.start) {
        start
    } else {
        endInclusive
    }
}