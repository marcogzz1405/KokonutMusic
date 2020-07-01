package mx.marco.kokonutstudio.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mx.marco.kokonutstudio.R
import mx.marco.kokonutstudio.domain.model.SearchModel
import mx.marco.kokonutstudio.ui.adapter.SuggestAdapter
import mx.marco.kokonutstudio.ui.base.BaseActivity
import mx.marco.kokonutstudio.ui.viewmodel.MainViewModel
import mx.marco.kokonutstudio.utils.*
import javax.inject.Inject

class MainActivity : BaseActivity(), SuggestAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        suggests.layoutManager = LinearLayoutManager(this)

        subscribeLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_left, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editext = searchView.findViewById<EditText>(R.id.search_src_text)
            editext.hint = "Search here..."
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("MainActivity", "onQueryTextSubmit: $query")
                    suggests.visibility = View.GONE
                    txtTitle.text = "Type the song you want de lyric"
                    txtTitle.visibility = View.VISIBLE
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    Log.d("MainActivity", "onQueryTextChange: $newText")
                    viewModel.searchSuggest(newText)
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun subscribeLiveData() {
        viewModel.searchLiveData.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    //showProgress()
                    txtTitle.visibility = View.VISIBLE
                    txtTitle.text = "Buscando..."
                }
                Status.ERROR -> {
                    hideProgress()
                    showErrorMessage("Ha ocurrido un error")
                }
                Status.SUCCESS -> {
                    hideProgress()
                    suggests.visibility = View.VISIBLE
                    txtTitle.visibility = View.GONE
                    suggests.adapter = SuggestAdapter(it?.data?: arrayListOf(), this)
                }
            }
        })
    }

    override fun onItemClick(item: SearchModel) {
        goToActivity(
            DetailsActivity::class.java,
            extras = Bundle().apply {
                putString("artist", item.artist?.name)
                putString("title", item.titleShort)
                putString("picture", item.artist?.pictureBig)
            }
        )
    }

}
