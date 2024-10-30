package al.bruno.exchanger.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineScopesModule = module {

    // Provides a CoroutineScope for UI-related tasks, running on the main thread.
    single(named("MainScope")) {
        CoroutineScope(SupervisorJob() + get<CoroutineDispatcher>(named("Main")))
    }

    // Provides a CoroutineScope optimized for I/O operations like network requests and file access.
    single(named("IOScope")) {
        CoroutineScope(SupervisorJob() + get<CoroutineDispatcher>(named("Default")))
    }

    // Provides a CoroutineScope designed for CPU-intensive tasks and complex calculations.
    single(named("DefaultScope")) {
        CoroutineScope(SupervisorJob() + get<CoroutineDispatcher>(named("Default")))
    }

    // Provides a CoroutineScope that starts in the calling thread and continues in the resumed continuation's thread.
    // Typically not recommended for general use due to unpredictable behavior.
    single(named("UnconfinedScope")) {
        CoroutineScope(SupervisorJob() + get<CoroutineDispatcher>(named("Unconfined")))
    }
}