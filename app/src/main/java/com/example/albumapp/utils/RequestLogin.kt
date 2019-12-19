package com.example.albumapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import com.example.albumapp.R
import com.example.albumapp.dto.RequestLoginDTO
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.schedule

class RequestLogin(sharedPref: SharedPreferencesControl) {
    private final val catalogAPI = CatalogAPI().getCatalogApi()?.create(LoginService::class.java)
    private final val EMPTY_STRING = ""
    private final val sharedPref = sharedPref

    fun validateLogin( intent: Intent,
                       activity: Activity,
                       view: View,
                       email: String,
                       password: String,
                       persistUser: Boolean,
                       button: Button
                       )
    {
        var call: Call<RequestLoginDTO> = catalogAPI!!.getLoginSuccess(email, password)
        button.setEnabled(false)

        call.enqueue(object :Callback<RequestLoginDTO> {
            override fun onResponse( call: Call<RequestLoginDTO>,
                                     response: Response<RequestLoginDTO> ) {
                if(!response.isSuccessful){
                    val errorMessage = JSONObject(response.errorBody()!!.string()).get("message")
                    showSnackbar(errorMessage.toString(), view)
                    button.setEnabled(true)
//                    Timer().schedule(1100){ button.text = activity.resources.getText(R.string.loginButtonText) }

                }else{
                    if(persistUser){
                        response.body().let {
                            sharedPref.persistUserSession(it?.sessionToken.toString())
                        }
                    }
                    activity.startActivity(intent)
                    activity.finish()
                }
            }
            override fun onFailure(call: Call<RequestLoginDTO>, t: Throwable) {}
        })
    }

    private fun checkEmptyInput(email: String, password: String) : Boolean{
        if(email.equals(EMPTY_STRING) || password.equals(EMPTY_STRING))
            return true
        return false
    }

    private fun showSnackbar(message: String, view: View) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(Color.parseColor("#EB2929"))
        snackbar.show()
    }


}