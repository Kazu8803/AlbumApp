package com.example.albumapp.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.ClipData
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumapp.MainActivity
import com.example.albumapp.R
import com.example.albumapp.dto.ItemCatalogDTO
import kotlinx.android.synthetic.main.catalog_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCatalog {
    private final val catalogAPI = CatalogAPI().getCatalogApi()?.create(CatalogService::class.java)

//    fun requestCatalogDataList(view: View, recyclerView: RecyclerView){
//        var call: Call<List<ItemCatalogDTO>> = catalogAPI!!.getCatalogList()
//        call.enqueue(object : Callback<List<ItemCatalogDTO>> {
//            override fun onResponse(
//                call: Call<List<ItemCatalogDTO>>,
//                response: Response<List<ItemCatalogDTO>>
//            ) {
//                val catalog = response.body() as List<ItemCatalogDTO>
//                Log.d("CATALOG", catalog.toString())
//                generateCatalogDataList(catalog, view, recyclerView)
//            }
//
//            override fun onFailure(call: Call<List<ItemCatalogDTO>>, t: Throwable) {
//               Log.d("ERROR CALL: ", call.toString() + " - " + t.toString())
//            }
//
//        })
//    }
//
//    private fun generateCatalogDataList(catalogDataList: List<ItemCatalogDTO>,
//                                        view: View,
//                                        recyclerView: RecyclerView){
//        val adapter = CatalogAdapter(catalogDataList)
//        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//    }
}