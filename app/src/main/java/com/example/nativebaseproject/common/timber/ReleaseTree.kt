package com.example.nativebaseproject.common.timber

import timber.log.Timber


class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?,
                     message: String, t: Throwable?) {
        // Do nothing here
    }
}