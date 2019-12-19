package com.example.albumapp.fragments

import android.animation.LayoutTransition
import android.app.SharedElementCallback
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.transition.addListener
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.example.albumapp.R
import com.example.albumapp.dto.ItemCatalogDTO
import com.example.albumapp.utils.Teste
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_activity_main.*
import kotlinx.android.synthetic.main.item_description_layout.view.*
import kotlinx.android.synthetic.main.item_layout.*
import org.xmlpull.v1.XmlPullParser

class ItemDescriptionFragment : Fragment() {
    lateinit var priceItemDescriptionTextView: TextView
    lateinit var productNameItemDescriptionTextView: TextView
    lateinit var buyItemDescriptionButton: Button
    lateinit var imageItemDescription: ImageView
    lateinit var priceDivided: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createNavigationIconAndFunctionality()

        return inflater.inflate(R.layout.item_description_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        priceItemDescriptionTextView = view.findViewById(R.id.priceItemDescriptionTextView)
        productNameItemDescriptionTextView = view.findViewById(R.id.productNameItemDescriptionTextView)
        buyItemDescriptionButton = view.findViewById(R.id.buyItemDescriptionButton)
        imageItemDescription = view.findViewById(R.id.imageItemDescription)
        priceDivided = view.findViewById(R.id.priceDividedTextView)

        animationTransation()
        priceItemDescriptionTextView.text = arguments?.getString("price")
        productNameItemDescriptionTextView.text = arguments?.getString("product")
        Picasso.get().load(arguments?.getString("image")).into(imageItemDescription)
        priceDivided.text = arguments!!.getString("price")?.let { showPriceDivided(it) }

    }

    private fun showPriceDivided(fullPrice: String) : String{
        val priceDividedBy12 = fullPrice.replace("R$", "").toDouble() / 12
        val purchaseBy12 = "12 x R$ " + "%.2f".format(priceDividedBy12)
        return purchaseBy12
    }

    private fun createNavigationIconAndFunctionality(){
        activity?.appToolbar?.navigationIcon = getDrawable(requireContext(), R.drawable.ic_left_arrow)
        activity?.appToolbar?.setNavigationOnClickListener(
            { returnPreviousLayout() })
    }

    private fun returnPreviousLayout(){
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

    private fun loadImage(){
        Picasso.get().load(arguments?.getString("image")).into(imageItemDescription)
    }


//    @RequiresApi(21)
//    private fun addTransitionListener() {
//
//        val transition =  activity?.window?.sharedElementEnterTransition
//
//        transition?.addListener(object: Transition.TransitionListener{
//            override fun onTransitionEnd(transition: Transition ) {
//                loadImage()
//                transition.removeListener(this)
//            }
//            override fun onTransitionStart(transition: Transition) {}
//            override fun onTransitionCancel(transition: Transition) { transition.removeListener(this) }
//            override fun onTransitionPause(transition: Transition) {}
//            override fun onTransitionResume(transition: Transition) {}
//        })
//    }

    private fun animationTransation(){
        val MOVE_TIME:Long = 600
        val FADE_TIME:Long = 230
        val manager = activity?.supportFragmentManager
        val catalogFragment = manager?.findFragmentById(R.id.catalogLayout)
        val fragmentTransaction = manager?.beginTransaction()
        val exitFade = Fade()
        val enterFade = Fade()
        val enterTransitionSet = TransitionSet()

        exitFade.duration = 0
        catalogFragment?.exitTransition = exitFade

        enterTransitionSet.addTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.slide_left))
        enterTransitionSet.duration = MOVE_TIME
        enterTransitionSet.startDelay = FADE_TIME
        sharedElementEnterTransition = enterTransitionSet

        enterFade.startDelay = MOVE_TIME + FADE_TIME
        enterFade.duration = FADE_TIME
        catalogFragment?.exitTransition = enterFade
        enterTransition = enterFade

//        enterTransitionSet.addTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.no_transition))
        enterTransitionSet.duration = 0
        enterTransitionSet.startDelay = 0
        returnTransition = enterTransitionSet
    }

    companion object{ fun newInstance() = ItemDescriptionFragment() }

}
