package com.vmodev.mvvm.ui.utils

object StringUtils {
    fun isBlank(content: String?) = content == null || content.equals("")
}