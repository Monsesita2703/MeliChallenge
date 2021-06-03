package com.example.melichallenge.ui.searchItem

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.melichallenge.databinding.FragmentSearchBinding
import com.example.melichallenge.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchViewModel = searchViewModel
        setupObservers()
    }

    private fun setupObservers() {
        binding.btnSearch.setOnClickListener {
            binding.tvEmptyField.visibility = View.GONE
            if (MainActivity.isInternetAvailable) {
                searchItem()
            } else {
                binding.llConnectivityError.visibility = View.VISIBLE
            }
        }
    }

    private fun searchItem() {
        if (searchViewModel.isValidItem()) {
            hideKeyboard(requireActivity()!!)
            binding.tvEmptyField.visibility = View.GONE
            val directions = SearchFragmentDirections.
            actionSearchFragmentToItemListFragment(searchViewModel.itemToSearch.value!!)
            findNavController().navigate(directions)
        } else {
            binding.tvEmptyField.visibility = View.VISIBLE
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val inputManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            inputManager.hideSoftInputFromWindow(
                    activity.currentFocus?.windowToken,
                    InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}