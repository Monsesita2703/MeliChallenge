package com.example.melichallenge.ui.itemDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.example.melichallenge.data.models.DataItemResult
import com.example.melichallenge.databinding.FragmentItemDetailBinding
import com.example.melichallenge.utils.Resource
import com.example.melichallenge.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    @Inject
    lateinit var assistedFactory: ItemDetailViewModelAssistedFactory

    private val args: ItemDetailFragmentArgs by navArgs()

    private val itemDetailViewModel: ItemDetailViewModel by viewModels {
        ItemDetailViewModel.Factory(assistedFactory, args.itemId)
    }

    private lateinit var binding: FragmentItemDetailBinding
    private lateinit var utils: Utils

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils = Utils(requireContext())
        setupObservers()
    }

    private fun setupObservers() {
        itemDetailViewModel.itemDetail.observe(viewLifecycleOwner, Observer { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pbLoadList.visibility = View.GONE
                    it.data.let { result ->
                        if (!result.isNullOrEmpty())
                            bindItemDetail(result!![0])
                        else
                            binding.viewError.root.visibility = View.VISIBLE
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

    private fun bindItemDetail(item: DataItemResult) {
        binding.tvItemTitle.text = item.body.title
        binding.tvItemPrice.text = utils.formatPrice(item.body.price)
        binding.tvItemAvailableQuantity.text = utils.formatAvailableQuantity(item.body.available_quantity)
        binding.tvItemCondition.text = utils.formatCondition(item.body.condition)
        binding.tvItemSoldQuantity.text = utils.formatSoldQuantity(item.body.sold_quantity)
        binding.tvItemWarranty.text = utils.formatWarranty(item.body.warranty)
        binding.tvItemMercadoPago.text = utils.formatAcceptsMercadoPago(item.body.accepts_mercadopago)

        if (!item.body.pictures.isNullOrEmpty()) {
            val imageList = ArrayList<SlideModel>()
            item.body.pictures.forEach { picture ->
                imageList.add(SlideModel(picture.secure_url))
            }
            binding.imageSlider.setImageList(imageList)
        }

        binding.clItemDetail.visibility = View.VISIBLE
    }
}