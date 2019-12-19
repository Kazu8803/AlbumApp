package com.example.albumapp.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.albumapp.CatalogActivity
import com.example.albumapp.R
import com.example.albumapp.utils.RequestLogin
import com.example.albumapp.utils.SharedPreferencesControl
import kotlinx.android.synthetic.main.login_layout.view.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper

class LoginFragment : Fragment() {
    private lateinit var sharedPref: SharedPreferencesControl
    lateinit var emailLoginEditText: EditText
    lateinit var passwordLoginEditText: EditText
    lateinit var loginButton: Button
    lateinit var rememberUserCheckbox: CheckBox
    lateinit var frameContainer: ViewGroup
    lateinit var requestLogin : RequestLogin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frameContainer = container as ViewGroup
        return inflater.inflate(R.layout.login_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogIntent = Intent(context , CatalogActivity::class.java)

        emailLoginEditText = view.findViewById(R.id.emailLoginEditText)
        passwordLoginEditText = view.findViewById(R.id.passwordLoginEditText)
        loginButton = view.findViewById(R.id.loginButton)
        rememberUserCheckbox = view.findViewById(R.id.rememberUserCheckBox)
//        loginProgressBar = view.findViewById(R.id.loginProgressBar)

        requestLogin = RequestLogin(sharedPref)

        loginButton.setOnClickListener{
            showLoadingLogin()
            activity?.let { it1 ->
                requestLogin.validateLogin( catalogIntent,
                    it1,
                    frameContainer,
                    emailLoginEditText.text.toString(),
                    passwordLoginEditText.text.toString(),
                    rememberUserCheckbox.isChecked,
                    loginButton)
            }
            loginButton.hideKeyBoard()
            Handler().postDelayed({loginButton.text = resources.getText(R.string.loginButtonText)},1000 )
        }

    }

    fun addSharedPreferences(sharedPref: SharedPreferencesControl) {
        this.sharedPref = sharedPref
    }

    private fun View.hideKeyBoard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun hideKeyBoard() {
        loginButton.hideKeyBoard()
    }

    private fun showLoadingLogin() {
        val progressDrawable = context?.let { CircularProgressDrawable(it).apply {
                setStyle(CircularProgressDrawable.LARGE)
                setColorSchemeColors(Color.WHITE)
                val centerRadius = 4
                val strokeWidth = 8
                val size = (centerRadius + strokeWidth) / 2
                setBounds(0,0,size,size)
            }
        }

        val drawableSpan = object: DynamicDrawableSpan() {
            override fun getDrawable() = progressDrawable
        }

        val EMPTY_STRING = "  "

        val spannableString = SpannableString(EMPTY_STRING).apply {
            setSpan(drawableSpan, length - 2, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        progressDrawable?.start()

        val callback = object: Drawable.Callback {
            override fun unscheduleDrawable(who: Drawable, what: Runnable) { }
            override fun invalidateDrawable(who: Drawable) { loginButton.invalidate() }
            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) { }
        }

        progressDrawable?.callback = callback

        loginButton.text = spannableString
    }

    companion object{ fun newInstance() = LoginFragment() }

}

