package com.csapps.trendinggit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csapps.trendinggit.data.db.entities.RepoDetails
import com.csapps.trendinggit.databinding.RepositorySingleItemBinding

class RepositoryListAdapter(var repoList: List<RepoDetails> ):
    RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {


    class RepositoryViewHolder(private val context: Context,
                               private val itemBinding: RepositorySingleItemBinding):
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(repoDetails: RepoDetails){

            itemBinding.repoDetails = repoDetails
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RepositorySingleItemBinding.inflate(inflater)

        return RepositoryViewHolder(parent.context, itemBinding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size


}