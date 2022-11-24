package com.team.storeappassignment.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingViewModal:ViewModel(){
    //navigation
    lateinit var navigateTo: (String) -> Unit

    private val _name= MutableLiveData<String>()
    val name:LiveData<String>
    get() = _name
    fun setName(name:String){
        _name.value=name
    }

    lateinit var appNavigateTo:(String)->Unit




}