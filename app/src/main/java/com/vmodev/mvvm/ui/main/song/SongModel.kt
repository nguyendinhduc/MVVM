package com.vmodev.mvvm.ui.main.song

import androidx.lifecycle.MutableLiveData
import com.vmodev.mvvm.common.Constants
import com.vmodev.mvvm.data.local.AppDatabase
import com.vmodev.mvvm.data.model.ItemSong
import com.vmodev.mvvm.data.remote.ApiHelp
import com.vmodev.mvvm.data.remote.ApiSong
import com.vmodev.mvvm.data.remote.InteractCommon
import com.vmodev.mvvm.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import java.util.concurrent.Executor
import javax.inject.Inject

class SongModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    schedulers: Executor
) :
    BaseViewModel<SongCallBack>(appDatabase, interactCommon, schedulers) {
    private val api: ApiSong
    val obSong = MutableLiveData<MutableList<ItemSong>>()
        get

    init {
        api = ApiHelp.createRetrofit(endpoint = Constants.BASE_URL, formatDate = Constants.LIST_FORMAT_TIME).create(ApiSong::class.java)
    }

    fun getSongs(name: String): Disposable? {
        setIsLoading(true)
        return subscribeHasResultDispose(
                api.getSongs(name = name)
                        .subscribeOn(Schedulers.from(schedulers))
                        .observeOn(AndroidSchedulers.mainThread()),
                {
                    for (itemSong in it) {
                        itemSong.id = itemSong.id + "@@@" + name
                        itemSong.keySearch = name
                    }
                    //save database
                    makeFunOnOtherThread {
                        appDatabase.songDao().deleteFromKeySearch(name)
                        appDatabase.songDao().insertAll(it)
                    }
                    setIsLoading(false)
                    //send to obSong and obSong call back view register
                    obSong.value = it
                },
                {
                    setIsLoading(false)
                    if (it is UnknownHostException){
                        val  resultSong = appDatabase.songDao()
                            .findAllFromKeySearch(name)
                        if ( resultSong.size > 0){
                            obSong.value = resultSong
                            return@subscribeHasResultDispose
                        }
                    }

                    callBack?.get()?.error(ItemSong::class.java.name, it)
                })
    }

    fun getAllSongOffline() {
        obSong.value = appDatabase.songDao().findAll()
    }

}