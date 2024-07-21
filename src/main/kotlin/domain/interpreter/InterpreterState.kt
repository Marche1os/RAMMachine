package domain.interpreter

sealed interface InterpreterState {
    data object Init : InterpreterState
    data object Error : InterpreterState
}