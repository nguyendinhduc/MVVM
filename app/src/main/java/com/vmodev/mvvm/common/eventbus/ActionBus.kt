package com.vmodev.mvvm.common.eventbus

interface ActionBus<Data> :BaseAction {
    fun call(data: Data)
}