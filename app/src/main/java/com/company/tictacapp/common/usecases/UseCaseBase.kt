package com.company.tictacapp.common.usecases

abstract class UseCaseBase<P,R> {
    abstract fun execute(param: P): R
}