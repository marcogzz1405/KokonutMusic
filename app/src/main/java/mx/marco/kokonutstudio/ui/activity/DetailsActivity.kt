package mx.marco.kokonutstudio.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_details.*
import mx.marco.kokonutstudio.R
import mx.marco.kokonutstudio.ui.base.BaseActivity
import mx.marco.kokonutstudio.ui.viewmodel.DetailsViewModel
import mx.marco.kokonutstudio.utils.Status
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.math.abs

class DetailsActivity : BaseActivity() {

    var media : MediaPlayer? = null

    companion object {
        const val EXTRA_FORCE_DEFAULT = "EXTRA_FORCE_DEFAULT"

        const val ARTIST = "artist"
        const val TITLE = "title"
        const val PICTURE = "picture"

        var artist = ""
        var song = ""
        var picture = ""
    }

    @Inject
    lateinit var viewModel : DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.getStringExtra(ARTIST) != null){
            artist = intent.getStringExtra(ARTIST)?:""
            song = intent.getStringExtra(TITLE)?:""
            picture = intent.getStringExtra(PICTURE)?:""
        }

        parentAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
            try {
                val finalHeight = resources.getDimensionPixelSize(R.dimen.final_toolbar_height)
                val range =
                    appBar.totalScrollRange - resources.getDimensionPixelSize(R.dimen.title_container_height) - finalHeight
                val factor =
                    if (abs(offset).toFloat() / range.toFloat() >= 1) 1f else abs(offset).toFloat() / range.toFloat()
                val initialHeight = resources.getDimensionPixelSize(R.dimen.initial_toolbar_height)
                val dx = resources.getDimensionPixelSize(R.dimen.margin_medium) * factor
                val dy = (initialHeight - finalHeight) * factor
                val initialMargin = (finalHeight - initialHeight / 2)
                val finalMargin = finalHeight / 2
                with(products) {
                    val layoutParams = layoutParams as CoordinatorLayout.LayoutParams
                    layoutParams.setMargins(
                        0,
                        (initialMargin + factor * (finalMargin - initialMargin)).toInt(),
                        0,
                        0
                    )
                    this.layoutParams = layoutParams
                }
            } catch (ex: IllegalStateException){
                ex.printStackTrace()
            }
        })

        subscribeViewModel()
        viewModel.getLyrics(artist, song)
    }

    private fun subscribeViewModel() {
        viewModel.getLyricsLiveData.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    Glide.with(this)
                        .load(picture)
                        .into(imgKokonut)
                    txtTitle.text = song.toString()
                    txtBody.text = it.data?.lyrics
                }
                Status.ERROR -> {
                    hideProgress()
                    showErrorMessage("Ha ocurrido un error.")
                }
                Status.LOADING -> showProgress()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
