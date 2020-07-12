package com.vmodev.mvvm.data.remote

import com.vmodev.mvvm.common.Constants
import com.vmodev.mvvm.common.eventbus.ActionBus
import com.vmodev.realmmvp.eventbus.Bus

class InteractCommon {
    private val apiSong: ApiSong
    private val bus: Bus

    init {
        bus = Bus()
        apiSong = ApiHelp.createRetrofit(endpoint = Constants.BASE_URL, formatDate = Constants.LIST_FORMAT_TIME).create(ApiSong::class.java)
    }

    fun <T> register(idObjectPost: String, action: ActionBus<T>) {
        bus.register(idObjectPost, action)
    }

    fun <T> registerList(idObjectPost: String, action: ActionBus<MutableList<T>>) {
        bus.registerList(idObjectPost, action)
    }

    fun <T> unregister(idObjectPost: String, action: ActionBus<T>) {
        bus.unregister(idObjectPost, action)
    }


    fun <T> unregisterList(idObjectPost: String, action: ActionBus<MutableList<T>>) {
        bus.unregisterList(idObjectPost, action)
    }

    fun <T> post(idObjectPost: String, t: T) {
        bus.post(idObjectPost, t)
    }

    fun <T> postList(idObjectPost: String, t: MutableList<T>) {
        bus.postList(idObjectPost, t)
    }
}