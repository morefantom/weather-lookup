package com.prathamesh.weather_lookup.viewmodels

class State<out T> (private val content: T) {
    var hasBeenConsumed = false

    fun getIfNotConsumed(): T? {
        return if (hasBeenConsumed) {
            null
        } else {
            hasBeenConsumed = true
            content
        }
    }

    fun peek(): T = content
}