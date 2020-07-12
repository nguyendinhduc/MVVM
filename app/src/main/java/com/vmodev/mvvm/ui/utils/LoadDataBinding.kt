package com.vmodev.mvvm.ui.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.vmodev.mvvm.R
import com.vmodev.mvvm.ui.customview.GlideApp

class LoadDataBinding {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["bind:urlImage"], requireAll = false)
        fun AppCompatImageView.setUrlImage(urlImage: String?) {
            if (!StringUtils.isBlank(urlImage)) {
                GlideApp.with(this)
                    .load(urlImage)
                    .placeholder(R.drawable.ao_dai)
                    .error(R.drawable.ao_dai)
                    .into(this)
            }
        }
    }
}