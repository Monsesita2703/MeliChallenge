package com.example.melichallenge.ui.itemsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melichallenge.R
import com.example.melichallenge.data.models.Item
import com.example.melichallenge.utils.Utils

class ItemListAdapter(private val context: Context, private val listener: (Item) -> Unit):
        RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {

    inner class ItemListViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(itemList: List<Item>) = differ.submitList(itemList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_view, parent, false))
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val utils = Utils(context)
        val item = differ.currentList[position]

        val ivItem = holder.itemView.findViewById<ImageView>(R.id.iv_item_thumbnail)
        val tvItemTitle = holder.itemView.findViewById<TextView>(R.id.tv_item_title)
        val tvItemPrice = holder.itemView.findViewById<TextView>(R.id.tv_item_price)
        val tvItemCondition = holder.itemView.findViewById<TextView>(R.id.tv_item_condition)
        val tvItemFreeShipping = holder.itemView.findViewById<TextView>(R.id.tv_item_free_shipping)

        if (!item.thumbnail.isNullOrEmpty()) {
            Glide.with(holder.itemView)
                .load(item.thumbnail)
                .into(ivItem)
        }

        tvItemTitle.text = item.title
        tvItemPrice.text = utils.formatPrice(item.price)
        tvItemCondition.text = utils.formatCondition(item.condition)
        tvItemFreeShipping.text = utils.formatFreeShipping(item.shipping.free_shipping)

        holder.itemView.setOnClickListener{ listener(item!!) }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
