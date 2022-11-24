package com.team.storeappassignment.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class StoreDao(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int
)

data class FoodDetail(
    val name:String,
    val brand:String,
    val price:String,
    @DrawableRes val image:Int
)