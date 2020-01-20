package com.jdmccormack.common

/**
 * Helper class that can return an error type [E] and a success type [T].
 * This class is commonly used to handle network requests.
 */
sealed class Result<out E, out T> {
    data class Success<out T>(val value: T) : Result<Nothing, T>()
    data class Failure<out E>(val error: E) : Result<E, Nothing>()
}

/**
 * Function for mapping the Success portion of the [Result.javaClass] class.
 */
fun <E, T, T2> Result<E, T>.map(function: (T) -> (T2)): Result<E, T2> = when (this) {
    is Result.Success -> Result.Success(function(value))
    is Result.Failure -> this
}

/**
 * Function for mapping the Failure portion of the [Result.javaClass] class.
 */
fun <E, E2, T> Result<E, T>.mapError(function: (E) -> (E2)): Result<E2, T> = when (this) {
    is Result.Success -> this
    is Result.Failure -> Result.Failure(function(error))
}
