package com.adidev.breakingbad.ui.main


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
        viewModel.visibleList.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                Toast.makeText(context,"No characters found", Toast.LENGTH_SHORT).show()
            }
            adapter.update(it)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        (menu.findItem(R.id.actionSearch).actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                viewModel.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}