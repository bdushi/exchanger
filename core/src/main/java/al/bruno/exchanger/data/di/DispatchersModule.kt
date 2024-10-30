package al.bruno.exchanger.data.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    // Provides a CoroutineScope for UI-related tasks, running on the main thread.
    single(named("Main")) { Dispatchers.Main }

    // Provides a CoroutineScope optimized for I/O operations like network requests and file access.
    single(named("IO")) { Dispatchers.IO }

    // Provides a CoroutineScope designed for CPU-intensive tasks and complex calculations.
    single(named("Default")) { Dispatchers.Default }

    // Provides a CoroutineScope that starts in the calling thread and continues in the resumed continuation's thread.
    // Typically not recommended for general use due to unpredictable behavior.
    single(named("Unconfined")) { Dispatchers.Unconfined }
}