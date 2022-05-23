package com.adidev.breakingbad.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adidev.breakingbad.R
import com.adidev.breakingbad.data.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.appbar.MaterialToolbar
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
        view.findViewById<TextView>(R.id.characterStatus).text = character.status
        view.findViewById<TextView>(R.id.characterNickname).text = character.nickname
        view.findViewById<TextView>(R.id.characterSeasonAppearance).text = character.appearance.joinToString(", ")
        val toolbar = view.findViewById<Toolbar>(R.id.myToolbar)
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_characterDetailFragment_to_characterListFragment)
        }
        return view
    }

}