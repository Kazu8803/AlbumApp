package com.example.albumapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.albumapp.R

class CreateAccountFragment : Fragment() {
    lateinit var nameCreateAccountEditText: EditText
    lateinit var surnameCreateAccountEditText: EditText
    lateinit var emailCreateAccountEditText: EditText
    lateinit var passwordCreateAccountEditText: EditText
    lateinit var repeatPasswordCreateAccountEditText: EditText
    lateinit var createAccountButton: Button
    lateinit var returnToLoginTextView: TextView


    companion object{ fun newInstance() = CreateAccountFragment() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameCreateAccountEditText = view.findViewById(R.id.nameCreateAccountEditText)
        surnameCreateAccountEditText = view.findViewById(R.id.surnameCreateAccountEditText)
        emailCreateAccountEditText = view.findViewById(R.id.emailCreateAccountEditText)
        passwordCreateAccountEditText = view.findViewById(R.id.passwordCreateAccountEditText)
        repeatPasswordCreateAccountEditText = view.findViewById(R.id.repeatPasswordCreateAccountEditText)
        createAccountButton = view.findViewById(R.id.createAccountButton)
        returnToLoginTextView = view.findViewById(R.id.returnToLoginTextView)


    }
}