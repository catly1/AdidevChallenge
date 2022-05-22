package com.adidev.breakingbad.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.R


class CharacterListFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_list_fragment, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application as BreakingBadApplication))[CharacterListViewModel::class.java]
        val adapter = CharacterListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.characterList.observe(viewLifecycleOwner){
            adapter.update(it)
        }
        return view
    }
}