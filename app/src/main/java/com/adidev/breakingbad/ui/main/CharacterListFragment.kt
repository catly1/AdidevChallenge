package com.adidev.breakingbad.ui.main


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.R


class CharacterListFragment : Fragment() {

    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_list_fragment, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application as BreakingBadApplication))[CharacterListViewModel::class.java]
        val adapter = CharacterListAdapter(findNavController())
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


        val toolbar = view.findViewById<Toolbar>(R.id.myToolbar)
        toolbar.inflateMenu(R.menu.search_menu)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.actionSearch -> {
                    val searchView = (it.actionView as SearchView)
//                    searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).setImageResource(
//                        R.drawable.baseline_close_white_24dp)
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            viewModel.filter(newText)
                            return false
                        }
                    })
                    true
                }
                else -> false
            }
        }


        return view
    }
}