package com.gopay.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gopay.databinding.FullScreenViewBinding
import com.gopay.databinding.ItemPeopleBinding

class PeopleLoadStateAdapter: LoadStateAdapter<PeopleLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
         holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder=
        LoadStateViewHolder(
            FullScreenViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    inner  class LoadStateViewHolder(val binding: FullScreenViewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState) {
            with(binding) {
                loader.isVisible = loadState is LoadState.Loading
                errorView.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorText.text = (loadState as? LoadState.Error)?.error?.message
            }

        }
    }


}