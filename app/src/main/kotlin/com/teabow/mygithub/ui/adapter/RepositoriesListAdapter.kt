package com.teabow.mygithub.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.teabow.mygithub.model.Repository
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.wrapContent

/**
 * Created by thibaud.bourgeois on 15/01/2017.
 * Repositories list adapter.
 */
class RepositoriesListAdapter(val repositoriesList: List<Repository>):
        RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder = RepositoryViewHolder(
            TextView(parent?.context).apply {
                textSize = 20f
                padding = dip(8)
                layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            }
    )

    override fun onBindViewHolder(holder: RepositoryViewHolder?, position: Int) {
        holder?.textView?.text = repositoriesList[position].name
    }

    override fun getItemCount(): Int {
        return repositoriesList.size
    }
}

class RepositoryViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)