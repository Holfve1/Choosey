package com.example.choosey

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChooseyViewModel : ViewModel() {
    private val _choices = mutableStateListOf<String>()
    val choices: List<String> get() = _choices

    fun toggle(option: String) {
        if (option in _choices) _choices.remove(option) else _choices.add(option)
    }

    // was random(); make it safe for empty lists
    fun pickRandom(): String? = _choices.randomOrNull()

    fun clear() = _choices.clear()
}