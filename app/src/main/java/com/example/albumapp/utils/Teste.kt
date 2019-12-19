package com.example.albumapp.utils

import android.util.Log

class Teste {

    companion object {
        var teste: Teste? = null
        fun getInstance(): Teste? {
            if (teste == null) {
                Log.d("TESTE", "FOI AQUI")
                teste = Teste()
            }
            else{
                Log.d("TESTE", "PASSEI PELO FALSE")
            }
            return teste
        }
    }

}
