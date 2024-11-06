package al.bruno.exchanger.common.core

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String? = "General Error") : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}

fun <T, R> Result.Success<List<T>>.transformAndFilter(
    transform: (T) -> R,
    filter: (R, String) -> Boolean,
    query: String
): List<R> {
    return this.data
        .map(transform)
        .filter { filter(it, query) }
}