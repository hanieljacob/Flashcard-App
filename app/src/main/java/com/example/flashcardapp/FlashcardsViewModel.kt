package com.example.flashcardapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val COMPLETED_FLASHCARDS = "COMPLETED_FLASHCARDS"
const val CURRENT_SCORE = "CURRENT_SCORE"
const val IS_GENERATE_BUTTON_DISABLED = "IS_GENERATE_BUTTON_DISABLED"
class FlashcardsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var problemBank = generate10Problems()
    private fun generate10Problems(): List<Problem> {
        val problems = List(10) {
            Problem(
                Random.nextInt(1, 100),
                Random.nextInt(0, 2),
                Random.nextInt(1, 21))
        }
        return problems
    }

    fun restartFlashcards() {
        problemBank = generate10Problems()
        currentIndex = 0
        completedFlashcards = false
    }

    private var currentIndex: Int
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    var currentScore: Int
        get() = savedStateHandle[CURRENT_SCORE] ?: 0
        set(value) = savedStateHandle.set(CURRENT_SCORE, value)

    var completedFlashcards: Boolean
        get() = savedStateHandle[COMPLETED_FLASHCARDS] ?: false
        set(value) = savedStateHandle.set(COMPLETED_FLASHCARDS, value)

    var isGenerateButtonDisabled: Boolean
        get() = savedStateHandle[IS_GENERATE_BUTTON_DISABLED] ?: false
        set(value) = savedStateHandle.set(IS_GENERATE_BUTTON_DISABLED, value)
    val currentQuestionFirstOperand: Int
        get() = problemBank[currentIndex].firstOperand

    val currentQuestionOperator: Int
        get() = problemBank[currentIndex].operator

    val currentQuestionSecondOperand: Int
        get() = problemBank[currentIndex].secondOperand

    val currentQuestionAnswer: Int
        get() = problemBank[currentIndex].answer
    val totalProblems: Int
        get() = problemBank.size

    fun incrementScore() {
        currentScore += 1
    }
    fun moveToNext() {
        if (!completedFlashcards) {
            currentIndex += 1
            if (currentIndex == totalProblems) {
                completedFlashcards = true
                isGenerateButtonDisabled = false
            }
        }
    }

}