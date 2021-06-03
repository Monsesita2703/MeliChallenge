package com.example.melichallenge.ui.itemsList

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melichallenge.databinding.FragmentItemListBinding
import com.example.melichallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    @Inject
    lateinit var assistedFactory: ItemListViewModelAssistedFactory

    private val args: ItemListFragmentArgs by navArgs()
    private val itemListViewModel: ItemListViewModel by viewModels {
        ItemListViewModel.Factory(assistedFactory, args.itemToSearch)
    }

    private lateinit var binding: FragmentItemListBinding
    private lateinit var adapter: ItemListAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = ItemListAdapter(requireContext()) { item ->
            val directions = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(item.id)
            findNavController().navigate(directions)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
        else
            binding.rvItems.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvItems.adapter = adapter
    }

    private fun setupObservers() {
        itemListViewModel.items.observe(viewLifecycleOwner, Observer { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pbLoadList.visibility = View.GONE
                    it.data.let { result ->
                        result.let {
                            if (!it!!.results.isNullOrEmpty())
                                adapter.submitList(it!!.results)
                            else
                                binding.viewEmptyList.root.visibility = View.VISIBLE
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    binding.pbLoadList.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                }
                Resource.Status.LOADING -> {
                    binding.pbLoadList.visibility = View.VISIBLE
                }
            }
        })
    }
}