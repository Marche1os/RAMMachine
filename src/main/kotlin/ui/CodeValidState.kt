package ui

sealed interface CodeValidState {

    data object Valid : CodeValidState

    data object Invalid : CodeValidState
}