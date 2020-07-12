package com.vmodev.mvvm.ui.main.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vmodev.mvvm.data.model.ItemSong
import com.vmodev.mvvm.databinding.ItemSongBinding

class SongAdapter : RecyclerView.Adapter<SongAdapter.Companion.SongViewHolder> {
    private val inter: ISongAdapter

    constructor(inter: ISongAdapter) {
        this.inter = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return SongViewHolder(binding)
    }

    override fun getItemCount() = inter.count()
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.binding.itemSong=inter.getData(position)
        holder.binding.executePendingBindings()
    }

    companion object {
        class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)
    }

    interface ISongAdapter {
        fun count(): Int
        fun getData(position: Int): ItemSong
    }
}