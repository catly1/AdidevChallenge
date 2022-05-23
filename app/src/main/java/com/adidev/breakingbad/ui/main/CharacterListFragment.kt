package com.adidev.breakingbad.ui.main


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.R
import com.adidev.breakingbad.databinding.CharacterListFragmentBinding


class CharacterListFragment : Fragment() {
    private var currentDialog : DialogFragment? = null
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_list_fragment, container, false)
        val binding: CharacterListFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.character_list_fragment, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application as BreakingBadApplication))[CharacterListViewModel::class.java]
        val adapter = CharacterListAdapter(findNavController())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.visibleList.observe(viewLifecycleOwner){

            if (it.isEmpty()){
                Toast.makeText(context,"No characters found", Toast.LENGTH_SHORT).show()
            }
            adapter.update(it)
        }
        setHasOptionsMenu(true)

        binding.myToolbar.inflateMenu(R.menu.search_menu)
        binding.myToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.actionSearch -> {
                    val searchView = (it.actionView as SearchView)
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

                R.id.filter -> {
                    currentDialog = FilterDialog(viewModel)
                    currentDialog?.show(parentFragmentManager, javaClass.simpleName)
                    false
                }
                else -> false
            }
        }


        return binding.root
    }
}