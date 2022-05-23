package com.adidev.breakingbad.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adidev.breakingbad.R
import com.adidev.breakingbad.data.Character
import com.adidev.breakingbad.databinding.CharacterDetailFragmentBinding
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
        val binding: CharacterDetailFragmentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.character_detail_fragment,container,false)
        binding.character = character
        Glide.with(binding.root).load(character.img).transform(FaceCrop(), RoundedCorners(50)).into(binding.characterImage)
        binding.myToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24dp)
        binding.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_characterDetailFragment_to_characterListFragment)
        }
        return binding.root
    }

}