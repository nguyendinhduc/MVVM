package com.vmodev.mvvm.ui.base.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.vmodev.mvvm.common.Constants
import com.vmodev.mvvm.data.local.AppDatabase
import com.vmodev.mvvm.data.model.api.IBaseResponse
import com.vmodev.mvvm.data.remote.InteractCommon
import com.vmodev.mvvm.ui.base.callback.BaseCallBack
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

abstract class BaseViewModel<CallBack : BaseCallBack> : ViewModel {
    protected val appDatabase: AppDatabase
    protected val interactCommon: InteractCommon
    protected val schedulers: Executor
    var callBack: WeakReference<CallBack>? = null
    protected val mDiableAll: CompositeDisposable
    protected val mIsLoading = ObservableBoolean(false)
    private var mIsDestroy: Boolean

    constructor(appDatabase: AppDatabase,
                interactCommon: InteractCommon,
                schedulers: Executor) {
        this.appDatabase = appDatabase
        this.schedulers = schedulers
        this.interactCommon = interactCommon
        this.mDiableAll = CompositeDisposable()
        mIsDestroy = false
    }


    override fun onCleared() {
        mDiableAll.dispose()
        super.onCleared()
    }

    fun isLoading() = mIsLoading
    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }


    companion object {
        fun checkExpire(data: IBaseResponse): Boolean {
            return data.getErrorCode() == Constants.CODE_TOKEN_EXPIRE || data.getStatus() == Constants.CODE_TOKEN_EXPIRE
        }

        fun checkExpire(data: Throwable): Boolean {
            if (data is retrofit2.HttpException) {
                if (data.code() == Constants.CODE_TOKEN_EXPIRE) {
                    return true
                }
            }
            return false
        }
    }


    protected fun <T : IBaseResponse> subscribeHasDispose(observable: Observable<T>?, onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
        if (observable == null) {
            return
        }
        mDiableAll.add(observable.subscribe(
                {
                    if (mIsDestroy) {
                        return@subscribe
                    }

//                    if (AccountInteraction.checkFinishCallApi(it)) {
//                    onNext.invoke(it)
//                    } else {
//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        }
//                        if (Constants.DEBUG && it.getMsg() != null) {
//                            mView.showMessage(it.getMsg()!!)
//                        }
//                        onError(Throwable(it.getMsg()))
//                    }
                    onNext(it)
                },
                {
                    run {
                        if (Constants.DEBUG) {
                            it.printStackTrace()
//                            if (it.message != null) {
//                                mView.showMessage(it.message!!)
//                            }
                        }
                        if (mIsDestroy) {
                            return@run
                        }

//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        } else {
//                            if (Constants.DEBUG) {
//                                mView.showMessage(it.message!!)
//                            }
                    }
                    onError(it)
                }))

    }

    protected fun <T : MutableList<out IBaseResponse>> subscribeListHasDispose(observable: Observable<T>?, onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
        if (observable == null) {
            return
        }
        mDiableAll.add(observable.subscribe(
                {
                    if (mIsDestroy) {
                        return@subscribe
                    }
//                    if (it.size == 0) {
//                        onError(Throwable("Can not connect"))
//                        return@subscribe
//                    }
//                    for (res in it) {
//                        if (AccountInteraction.checkFinishCallApi(res)) {
//
//                        } else {
//                            if (checkExpire(res)) {
//                                (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                            }
////                            if (Constants.DEBUG && res.getMsg() != null) {
////                                mView.showMessage(res.getMsg()!!)
////                            }
//                            onError(Throwable(res.getMsg()))
//                            break
//                        }
//                    }
                    onNext(it)
                },
                {
                    run {
                        if (Constants.DEBUG) {
                            it.printStackTrace()
//                            if (it.message != null) {
//                                mView.showMessage(it.message!!)
//                            }
                        }
                        if (mIsDestroy) {
                            return@run
                        }

//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        } else {
////                            if (Constants.DEBUG) {
////                                mView.showMessage(it.message!!)
////                            }
//                        }
                        onError(it)
                    }
                }));

    }

    protected fun <T> subscribeListHasResultDispose(observable: Observable<T>?, onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable? {
        if (observable == null) {
            return null
        }
        return observable.subscribe(
                {
                    if (mIsDestroy) {
                        return@subscribe
                    }
//                    if (it.size == 0) {
//                        onError(Throwable("Can not connect"))
//                        return@subscribe
//                    }
//                    for (res in it) {
//                        if (AccountInteraction.checkFinishCallApi(res)) {
//
//                        } else {
//                            if (checkExpire(res)) {
//                                (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                            }
////                            if (Constants.DEBUG && res.getMsg() != null) {
////                                mView.showMessage(res.getMsg()!!)
////                            }
//                            onError(Throwable(res.getMsg()))
//                            break
//                        }
//                    }
                    onNext(it)
                },
                {
                    run {
                        if (Constants.DEBUG) {
                            it.printStackTrace()
//                            if (it.message != null) {
//                                mView.showMessage(it.message!!)
//                            }
                        }
                        if (mIsDestroy) {
                            return@run;
                        }

//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        } else {
////                            if (Constants.DEBUG) {
////                                mView.showMessage(it.message!!)
////                            }
//                        }
                        onError(it)
                    }
                })

    }


    @SuppressLint("CheckResult")
    protected fun <T> subscribeNotDispose(observable: Observable<T>?, onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
        if (observable == null) {
            return
        }
        observable.subscribe(
                {
                    if (mIsDestroy) {
                        return@subscribe
                    }
                    onNext(it)
                },
                {
                    run {
                        if (Constants.DEBUG) {
                            it.printStackTrace();
                        }
                        if (mIsDestroy) {
                            return@run;
                        }
                        onError(it)
                    }
                })
    }


    protected fun <T> subscribeHasResultDispose(observable: Observable<T>?, onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable? {
        if (observable == null) {
            return null
        }
        return observable.subscribe(
                {
                    if (mIsDestroy) {
                        return@subscribe
                    }
//                    if (AccountInteraction.checkFinishCallApi(it)) {
                    onNext(it)
//                    } else {
//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        }
////                        if (Constants.DEBUG && it.getMsg() != null) {
////                            mView.showMessage(it.getMsg()!!)
////                        }
//                        onError.invoke(Throwable(it.getMsg()))
//                    }
                },
                {
                    run {
                        if (Constants.DEBUG) {
                            it.printStackTrace()
//                            if (it.message != null) {
//                                mView.showMessage(it.message!!)
//                            }
                        }
                        if (mIsDestroy) {
                            return@run
                        }

//                        if (checkExpire(it)) {
//                            (DatPhuongApplication.context as DatPhuongApplication).errorAuthentication()
//                        } else {
////                            if (Constants.DEBUG) {
////                                mView.showMessage(it.message!!)
////                            }
//                        }
                        onError(it)
                    }
                })

    }

    protected fun makeFunOnOtherThread(method: () -> Unit) {
        Observable.create((ObservableOnSubscribe<Boolean> {
            method()
            it.onNext(true)
            it.onComplete()
        }))
                .subscribeOn(Schedulers.from(schedulers))
                .subscribe()
    }

}
