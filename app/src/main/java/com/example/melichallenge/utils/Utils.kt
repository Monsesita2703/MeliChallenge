package com.example.melichallenge.utils

import android.content.Context
import com.example.melichallenge.R
import com.example.melichallenge.data.models.Shipping

class Utils (
    private val context: Context
) {

    fun formatPrice(price: Double): String {
        return String.format(context.getString(R.string.str_item_price), price)
    }

    fun formatAvailableQuantity(availableQuantity: Int): String {
        return if (availableQuantity == 0)
            context.getString(R.string.str_item_not_available_quantity)
        else
            String.format(context.getString(R.string.str_item_available_quantity, availableQuantity))
    }

    fun formatCondition(condition: String): String {
        return if (condition == "new")
            context.getString(R.string.str_item_new)
        else
            context.getString(R.string.str_item_not_new)
    }

    fun formatSoldQuantity(soldQuantity: Int): String {
        return if (soldQuantity == 0)
            context.getString(R.string.str_item_not_sold_quantity)
        else
            String.format(context.getString(R.string.str_item_sold_quantity, soldQuantity))
    }

    fun formatAcceptsMercadoPago(acceptsMercadoPago: Boolean): String {
        return if (acceptsMercadoPago)
            context.getString(R.string.str_item_accepts_mercado_pago)
        else
            context.getString(R.string.str_item_not_accepts_mercado_pago)
    }

    fun formatWarranty(warranty: String?): String {
        return if (!warranty.isNullOrEmpty())
            String.format(context.getString(R.string.str_item_warranty), warranty)
        else
            context.getString(R.string.str_item_warranty_no_information)
    }

    fun formatFreeShipping(freeShipping: Boolean): String {
        return if (freeShipping)
            context.getString(R.string.str_item_free_shipping)
        else
            context.getString(R.string.str_empty)

    }
}