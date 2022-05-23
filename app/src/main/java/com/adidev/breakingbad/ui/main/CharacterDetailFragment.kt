package com.adidev.breakingbad.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adidev.breakingbad.R
import com.adidev.breakingbad.data.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msomu.glide.facetransformation.FaceCrop

class CharacterDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val character : Character = arguments?.get("character") as Character
        val view = inflater.inflate(R.layout.character_detail_fragment, container, false)
        Glide.with(view).load(character.img).transform(FaceCrop(), RoundedCorners(50)).into(view.findViewById(R.id.characterImage))
        view.findViewById<TextView>(R.id.characterOccupation).text = character.occupation.joinToString(", " )
        view.findViewById<TextView>(R.id.characterName).text = character.name
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.back_menu, menu)

        menu.findItem(R.id.backButton).actionView.setOnClickListener {
            println("it's working")
            findNavController().popBackStack()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}