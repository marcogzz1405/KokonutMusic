package mx.marco.kokonutstudio.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_splash.*
import mx.marco.kokonutstudio.R
import mx.marco.kokonutstudio.utils.goToActivity

class SplashActivity : AppCompatActivity() {

    var DURATION = 1000
    var TIMER_LATER = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = DURATION.toLong()
        fadeIn.startOffset = TIMER_LATER.toLong()
        fadeIn.fillAfter = true

        var fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = DURATION.toLong()
        fadeOut.startOffset = TIMER_LATER.toLong()
        fadeOut.fillAfter = true

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                txtSplash.visibility = View.VISIBLE
                imgSplash.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                txtSplash.startAnimation(fadeOut)
                imgSplash.startAnimation(fadeOut)
            }
        })

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                goToActivity(
                    MainActivity::class.java
                )
                finish()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        imgSplash.startAnimation(fadeIn)
        txtSplash.startAnimation(fadeIn)

    }
}
