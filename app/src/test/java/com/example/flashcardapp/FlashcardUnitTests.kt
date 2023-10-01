package com.example.flashcardapp
import org.junit.Test
import org.junit.Assert.*
import androidx.lifecycle.SavedStateHandle

class FlashcardUnitTests {

    @Test
    fun completedFlashcardsFlag_isCorrect() {
        val savedStateHandle = SavedStateHandle(mapOf("COMPLETED_FLASHCARDS" to false))
        val flashcardsViewModel = FlashcardsViewModel(savedStateHandle)
        assertEquals(false, flashcardsViewModel.completedFlashcards)
        for (i in 1..10) {
            flashcardsViewModel.moveToNext()
        }
        assertEquals(true, flashcardsViewModel.completedFlashcards)
    }
}