package com.vmodev.mvvm.ui.base.callback

import com.vmodev.mvvm.ui.base.BaseViewUI

interface BaseCallBack : BaseViewUI{
    fun error(id: String, error: Throwable)
}