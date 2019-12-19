package com.example.albumapp.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.core.os.postDelayed
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.example.albumapp.CatalogActivity
import com.example.albumapp.MainActivity
import com.example.albumapp.R
import com.example.albumapp.dto.ItemCatalogDTO
import com.example.albumapp.utils.*
import kotlinx.android.synthetic.main.catalog_activity_main.*
import kotlinx.android.synthetic.main.item_description_layout.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class CatalogFragment : Fragment()  {
    private lateinit var frameContainer: ViewGroup
    private lateinit var catalogRecyclerView: RecyclerView
    private val catalogAPI = CatalogAPI().retrofit.create(CatalogService::class.java)
    private val adapter = CatalogAdapter()
    private lateinit var swipeCatalog: SwipeRefreshLayout
    private lateinit var catalogActivity: CatalogActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frameContainer = container as ViewGroup
        activity?.appToolbar?.navigationIcon = null
        return inflater.inflate(R.layout.catalog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemDecor = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        catalogActivity = CatalogActivity()
        swipeCatalog = view.findViewById(R.id.catalogLayoutSwipeRefresh)
        catalogRecyclerView = view.findViewById(R.id.catalogLayoutRecyclerView)
        itemDecor.setDrawable(this.context?.resources!!.getDrawable(R.drawable.recyclerview_divider, null))
        catalogRecyclerView.addItemDecoration(itemDecor)

        adapter.clickableCallback = {
            postponeEnterTransition()
            transferItemCatalogata(it)
            startPostponedEnterTransition()
//            sendDataToItemDescriptionFragment(it)
        }
        catalogRecyclerView.adapter = adapter

        getCatalogResponse()
        swipeCatalog.setOnRefreshListener {
            getCatalogResponse()
        }
    }

    private fun getCatalogResponse() {
        swipeCatalog.isRefreshing = true

        catalogAPI.getCatalogList().enqueue(object : Callback<MutableList<ItemCatalogDTO>> {
            override fun onResponse(
                call: Call<MutableList<ItemCatalogDTO>>,
                response: Response<MutableList<ItemCatalogDTO>>
            ) {
                val catalog = response.body() as MutableList<ItemCatalogDTO>
                adapter.updateCatalog(catalog)
                swipeCatalog.isRefreshing = false
            }

            override fun onFailure(call: Call<MutableList<ItemCatalogDTO>>, t: Throwable) {
                Log.d("T", t.toString())
                swipeCatalog.isRefreshing = false
            }
        })
    }

    private fun sendDataToItemDescriptionFragment(itemCatalogDTO: ItemCatalogDTO){
        val intent = Intent(activity?.baseContext, ItemDescriptionFragment::class.java)
        intent.putExtra("price", itemCatalogDTO.price)
        intent.putExtra("product", itemCatalogDTO.product)
        intent.putExtra("image", itemCatalogDTO.image)

        val activityOptions = makeSceneTransitionAnimation(
            catalogActivity,
            Pair(view?.findViewById(R.id.itemImageView), R.string.imageToTransit.toString())
        )
        context?.let { ActivityCompat.startActivity(it,intent,activityOptions.toBundle()) }
    }


    private fun transferItemCatalogata(itemCatalogDTO: ItemCatalogDTO) {
        val manager = activity?.supportFragmentManager
        val fragmentTransaction = manager?.beginTransaction()
        val itemDescriptionFragment = ItemDescriptionFragment.newInstance()

            itemDescriptionFragment.arguments = Bundle().apply {
                putString("price", itemCatalogDTO.price)
                putString("product", itemCatalogDTO.product)
                putString("image", itemCatalogDTO.image)


                fragmentTransaction?.setReorderingAllowed(true)?.
                    replace(R.id.catalogFrameContainer, itemDescriptionFragment)?.addToBackStack(null)?.apply {
                    addSharedElement(itemImageView, itemImageView.transitionName)
                }?.commit()
        }
    }

    companion object {
        fun newInstance() = CatalogFragment()
    }


}