package com.android.kotlindemo.utils

object Utils {


   open fun isCause(
        expected: Class<out Throwable?>,
        exc: Throwable?
    ): Boolean {
        return expected.isInstance(exc) || exc != null && isCause(expected, exc.cause)
    }
}