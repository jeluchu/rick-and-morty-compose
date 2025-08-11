package com.jeluchu.rickandmorty.core.interactor

import kotlinx.coroutines.flow.Flow

abstract class UseCase <out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Flow<Type>

    @JvmOverloads
    operator fun invoke(params: Params? = null): Flow<Type> = run(params)

    class None
}