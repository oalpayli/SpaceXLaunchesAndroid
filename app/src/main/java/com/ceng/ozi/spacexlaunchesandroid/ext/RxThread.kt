package com.ceng.ozi.spacexlaunchesandroid.ext

import com.ceng.ozi.spacexlaunchesandroid.app.di.module.RxThreadModule
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import javax.inject.Inject
import javax.inject.Named

class RxThread @Inject constructor(@Named(RxThreadModule.mainThread) private val mainSchedulers: Scheduler,
                                   @Named(RxThreadModule.ioThread) private val ioScheduler: Scheduler) {

    fun <T> applySync(): ObservableTransformer<T, T> {

        return ObservableTransformer {
            it.subscribeOn(ioScheduler)
                    .observeOn(mainSchedulers)
        }

    }

    fun <T> applySyncSingle(): SingleTransformer<T, T> {

        return SingleTransformer {
            it.subscribeOn(ioScheduler)
                .observeOn(mainSchedulers)
        }

    }

}