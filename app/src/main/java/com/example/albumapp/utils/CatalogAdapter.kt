package com.example.albumapp.utils

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumapp.R
import com.example.albumapp.dto.ItemCatalogDTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_layout.view.*

class CatalogAdapter :
    RecyclerView.Adapter<CatalogAdapter.CatalogAdapterViewHolder>() {
    private var catalogDataList = mutableListOf<ItemCatalogDTO>()
    lateinit var clickableCallback: (item: ItemCatalogDTO) -> Unit

    class CatalogAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priceTextView = itemView.findViewById<TextView>(R.id.priceTextView)
        val productNameTextView = itemView.findViewById<TextView>(R.id.productNameTextView)
        val itemImageView = itemView.findViewById<ImageView>(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return CatalogAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatalogAdapterViewHolder, position: Int) {
        holder.priceTextView?.text = catalogDataList[position].price
        holder.productNameTextView?.text = catalogDataList[position].product
        Picasso.get().load(catalogDataList[position].image).into(holder.itemImageView)
        holder.itemView.setOnClickListener {
            clickableCallback(catalogDataList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = catalogDataList.size

    fun updateCatalog(newCatalog: MutableList<ItemCatalogDTO>) {
        catalogDataList.clear()
        catalogDataList.addAll(newCatalog)
        notifyDataSetChanged()
    }


}