package al.bruno.exchanger.ui.foundation.arch

/**
 * What is the difference between StateFlow — SharedFlow — Channel?
 * With the shared flow, events are broadcast to an unknown number (zero or more) of subscribers.
 * In the absence of a subscriber, any posted event is immediately dropped.
 * It is a design pattern to use for events that must be processed immediately or not at all.
 */

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {
    // Create Initial State of View
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    // Get Current State
    val currentState: State
        get() = uiState.value

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect : Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()
  
}