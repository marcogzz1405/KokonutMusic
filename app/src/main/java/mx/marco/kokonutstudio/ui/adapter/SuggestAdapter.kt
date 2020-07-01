package mx.marco.kokonutstudio.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_suggest.view.*
import mx.marco.kokonutstudio.R
import mx.marco.kokonutstudio.domain.model.SearchModel
import mx.marco.kokonutstudio.ui.activity.MainActivity

class SuggestAdapter(val suggests: List<SearchModel>, val lister: MainActivity) : RecyclerView.Adapter<SuggestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_suggest, parent, false))
    }

    override fun getItemCount(): Int {
        return suggests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suggests[position])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                lister.onItemClick(suggests[adapterPosition])
            }
        }

        fun bind(search: SearchModel) {
            Glide.with(view)
                .load(search.artist?.picture)
                .into(view.suggestImage)
            view.suggestTitle.text = search.titleShort
            view.suggestName.text = search.artist?.name
        }
    }

    interface OnItemClickListener{
        fun onItemClick(item: SearchModel)
    }

}