package com.gopay.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gopay.R
import com.gopay.databinding.ItemPeopleBinding
import com.gopay.network.response.Peoples
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_people.view.*
import javax.inject.Inject

class PeopleAdapter @Inject constructor() : PagingDataAdapter<Peoples, PeopleAdapter.PeopleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder =
        PeopleViewHolder(
            ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }


    inner class PeopleViewHolder(val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(it: Peoples?) {
            binding.name.text = it?.name
            Picasso
                .get()
                .load("https://i.pravatar.cc/150?u=" + it?.name)
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.avatar);
        }


    }

    object diffCallback : DiffUtil.ItemCallback<Peoples>() {
        override fun areItemsTheSame(oldItem: Peoples, newItem: Peoples): Boolean {
          return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Peoples, newItem: Peoples): Boolean {
            return oldItem ==newItem
        }

    }
}




