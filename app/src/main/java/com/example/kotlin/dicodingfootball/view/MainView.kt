package com.example.kotlin.dicodingfootball.view

import android.os.Parcelable

interface MainView: Parcelable {
    fun showLoading()
    fun hideLoading()
    fun showWarning(message: String)
}