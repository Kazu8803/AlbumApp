package com.example.albumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.albumapp.fragments.CreateAccountFragment
import com.example.albumapp.fragments.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
//    private lateinit var auth: FirebaseAuth
    lateinit var goToCreateAccount: TextView
    lateinit var returnToLogin: TextView
    val loginFragment: LoginFragment = LoginFragment.newInstance()
    val createAccountFragment: CreateAccountFragment = CreateAccountFragment.newInstance()
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.frameContainer, loginFragment, "dynamic_fragment_layout")
            .commit()
    }

}
