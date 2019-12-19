package com.example.albumapp.enum

enum class LoginResponseCodeEnum (val value: Int) {
    SUCESS(200),
    FAIL_INCORRECT_INPUT(500),
    FAIL_EMPTY_INPUT(501)
}