package com.adidev.breakingbad.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.adidev.breakingbad.R
import com.adidev.breakingbad.data.Character

class CharacterDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val character : Character = arguments?.get("character") as Character
        val view = inflater.inflate(R.layout.character_detail_fragment, container, false)
        view.findViewById<TextView>(R.id.characterName).text = character.name
        return view
    }
}