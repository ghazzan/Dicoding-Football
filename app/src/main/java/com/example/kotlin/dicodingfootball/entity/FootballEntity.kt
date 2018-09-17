package com.example.kotlin.dicodingfootball.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootballEntity(
        val name: String,
        val image: Int,
        val description: String? = null): Parcelable