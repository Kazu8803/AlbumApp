package com.example.albumapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesControl {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val PREF_NAME = "user_logged"

    fun isUserLogged(): Boolean = if(sharedPreferences != null){
            Log.d("LOGGED: ", sharedPreferences!!.contains("user_logged").toString())
            sharedPreferences!!.contains(PREF_NAME)
        }else{
            false
        }

    fun instanceSharedPreferences(context: Context){
        if(sharedPreferences==null){
            Log.d("Condition: ", "SharedPreferences instance.")
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            Log.d("SHARED", sharedPreferences.toString())
            editor = sharedPreferences!!.edit()
        }else{
            Log.d("Condition: ", "SharedPreferences already instanced.")
        }
    }

    fun persistUserSession(token: String){
        if(!checkNullInstance()){
            editor?.putString(PREF_NAME, token)?.apply()
        }
        Log.d("Condition: ", "Token already instanced.")
    }

    fun getToken(): String?{
        return sharedPreferences?.getString(PREF_NAME, null)
    }

    private fun checkNullInstance(): Boolean{
        return sharedPreferences == null
    }
}