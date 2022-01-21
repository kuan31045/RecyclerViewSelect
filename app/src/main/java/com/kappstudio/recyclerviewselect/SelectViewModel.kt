package com.kappstudio.recyclerviewselect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Int>>(mutableListOf())
    val list: LiveData<List<Int>> = _list

    private val _singleSelection = MutableLiveData<Int>()
    val singleSelection: LiveData<Int> = _singleSelection

    private val _multipleSelection = MutableLiveData<List<Int>>(mutableListOf())
    val multipleSelection: LiveData<List<Int>> = _multipleSelection

    init {
        val mList = mutableListOf<Int>()
        repeat(100) {
            mList.add(it)
        }
        _list.value = mList
    }

    fun selectSingle(position: Int) {
        _singleSelection.value = position
    }

    fun selectMultiple(position: Int) {
        if (multipleSelection.value?.contains(position) == true) {
            _multipleSelection.value = _multipleSelection.value?.minus(position)
        } else {
            _multipleSelection.value = _multipleSelection.value?.plus(position)
        }
    }
}