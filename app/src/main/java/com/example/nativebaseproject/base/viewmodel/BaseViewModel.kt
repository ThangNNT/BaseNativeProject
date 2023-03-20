package com.example.nativebaseproject.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nativebaseproject.data.remote.base.Either
import com.example.nativebaseproject.data.remote.base.ErrorResponse
import com.example.nativebaseproject.data.remote.base.Result

@Suppress("unused")
abstract class BaseViewModel: ViewModel() {
    inline fun <reified T: Any>Either<ErrorResponse, T>.toResult(): Result<T> {
        var result: Result<T> = Result.Empty
        this.fold({
            result = Result.Error(it)
            Any()
        }, {
            result = Result.Success(it)
            Any()
        })
        return result
    }
}