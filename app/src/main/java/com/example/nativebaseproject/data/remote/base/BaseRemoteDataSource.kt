package com.example.nativebaseproject.data.remote.base


/**
 * check [this link](https://github.com/ThangNNT/JetpackComposeWithCleanArch/blob/main/data/remote/src/main/java/com/nnt/remote/datasource/RemoteMovieDataSource.kt)
 *  if you don't know how to use this class
 */
@Suppress("unused")
abstract class BaseRemoteDataSource {
    /**
     * wrap api result
     */
    protected suspend fun <T> getResult(call: suspend () -> T): Either<ErrorResponse, T> {
        return try {
            Either.Right(call.invoke())
        } catch (e: Exception) {
            Either.Left(ErrorResponse(e.message))
        }
    }
}