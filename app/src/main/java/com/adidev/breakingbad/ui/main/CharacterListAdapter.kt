package com.adidev.breakingbad.ui.main

import android.media.FaceDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adidev.breakingbad.R
import com.adidev.breakingbad.data.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msomu.glide.facetransformation.FaceCrop

class CharacterListAdapter() : RecyclerView.Adapter<CharacterListAdapter.ItemViewHolder>() {

    private var characterList: List<Character> = mutableListOf()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.characterName)
        val imageView: ImageView = view.findViewById(R.id.characterImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = characterList[position]
        holder.textView.text = item.name
        Glide.with(holder.imageView).load(item.img).transform(FaceCrop(), RoundedCorners(50)).into(holder.imageView)
    }

    fun update(fetchedList: List<Character>){
        characterList = fetchedList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characterList.size
}