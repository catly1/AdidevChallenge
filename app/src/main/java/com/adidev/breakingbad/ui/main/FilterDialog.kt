package com.adidev.breakingbad.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.R
import com.adidev.breakingbad.databinding.FilterDialogBinding

class FilterDialog(private val viewModel: CharacterListViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val application : BreakingBadApplication = requireActivity().application as BreakingBadApplication
        val binding : FilterDialogBinding = DataBindingUtil.inflate(layoutInflater,R.layout.filter_dialog,null,false)
        val dialog = AlertDialog.Builder(requireContext())
        setChecks(binding, application.appContainer.seasonFilter)
        binding.viewmodel = viewModel
        binding.checkBox.setOnCheckedChangeListener { compoundButton, b -> setOnClickAction(compoundButton, b, application.appContainer.seasonFilter) }
        binding.checkBox2.setOnCheckedChangeListener { compoundButton, b -> setOnClickAction(
            compoundButton,
            b,
            application.appContainer.seasonFilter
        )}
        binding.checkBox3.setOnCheckedChangeListener { compoundButton, b -> setOnClickAction(
            compoundButton,
            b,
            application.appContainer.seasonFilter
        ) }
        binding.checkBox4.setOnCheckedChangeListener { compoundButton, b -> setOnClickAction(
            compoundButton,
            b,
            application.appContainer.seasonFilter
        )}
        binding.checkBox5.setOnCheckedChangeListener { compoundButton, b -> setOnClickAction(
            compoundButton,
            b,
            application.appContainer.seasonFilter
        )}
        dialog.setView(binding.root)

        return dialog.create()
    }

    fun setOnClickAction(compoundButton: CompoundButton, b: Boolean, seasonFilter: MutableList<Int>) {
        val season : Int? = viewModel.keyMap[compoundButton.text]
        if (b){
            season?.let { seasonFilter.add(it) }
        } else {
            season?.let { seasonFilter.remove(season)}
        }
        viewModel.filterBySeason()
    }

    private fun setChecks(binding: FilterDialogBinding, seasonFilter: MutableList<Int>) {
        for (season in seasonFilter){
            when (season){
                1 -> binding.checkBox.isChecked = true
                2 -> binding.checkBox2.isChecked = true
                3 -> binding.checkBox3.isChecked = true
                4 -> binding.checkBox4.isChecked = true
                5 -> binding.checkBox5.isChecked = true
            }
        }
    }
}