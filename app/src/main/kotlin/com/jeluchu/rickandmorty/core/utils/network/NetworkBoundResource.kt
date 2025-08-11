package com.jeluchu.rickandmorty.core.utils.network


import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.network.Resource
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

inline fun <RequestType> networkResource(
    crossinline fetch: suspend () -> RequestType,
    crossinline shouldFetch: () -> Boolean = { true }
) = flow {

    emit(Resource.Loading())

    if (shouldFetch()) {

        try {
            emit(Resource.Success(fetch()))
        } catch (exception: IOException) {
            emit(Resource.Error(Failure.NetworkConnection(errorMessage = exception.message.orEmpty())))
        } catch (exception: Exception) {
            emit(Resource.Error(Failure.NetworkConnection(errorMessage = exception.message.orEmpty())))
        }

    } else emit(Resource.Error(Failure.NetworkConnection(errorMessage = "error. .orEmpty()")))

}

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: () -> Boolean = { true },
    crossinline dbTransform: (ResultType) -> RequestType,
) = flow {
    emit(Resource.Loading())

    val flow = if (shouldFetch()) {

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(dbTransform(it)) }
        } catch (exception: IOException) {
            query().map { Resource.Error(Failure.NetworkConnection(errorMessage = exception.message.orEmpty())) }
        } catch (error: ServerResponseException) {
            query().map { Resource.Error(Failure.ServerError(error.response.status.value, error.message)) }
        } catch (exception: Exception) {
            query().map { Resource.Error(Failure.NetworkConnection(errorMessage = exception.message.orEmpty())) }
        }

    } else query().map { Resource.Success(dbTransform(it)) }

    emitAll(flow)
}