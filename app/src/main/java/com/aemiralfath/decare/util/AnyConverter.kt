package com.aemiralfath.decare.util

object AnyConverter {
    fun toListString(value: Any) : MutableList<String> {
        var expectedReturn = mutableListOf<String>()
        if (value is MutableList<*>) {
            expectedReturn = value.filterIsInstance<String>().toMutableList()
        }
        return expectedReturn
    }

    fun toListChar(value: Any) : MutableList<Char> {
        var expectedReturn = mutableListOf<Char>()
        if (value is MutableList<*>) {
            expectedReturn = value.filterIsInstance<Char>().toMutableList()
        }
        return expectedReturn
    }
}