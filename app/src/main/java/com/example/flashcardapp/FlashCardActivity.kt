package com.example.flashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardapp.databinding.ActivityFlashCardBinding

class FlashCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlashCardBinding
    private val flashcardsViewModel: FlashcardsViewModel by viewModels()
    private fun disableButton(button: Button) {
        button.isEnabled = false
    }

    private fun enableButton(button: Button) {
        button.isEnabled = true
    }
    private fun displayCurrentProblem() {
        binding.answerEditText.text.clear()
        binding.operand1.text = flashcardsViewModel.currentQuestionFirstOperand.toString()
        binding.operator.text = if (flashcardsViewModel.currentQuestionOperator == 0) "+" else "-"
        binding.operand2.text = flashcardsViewModel.currentQuestionSecondOperand.toString()

    }

    private fun displayBlankProblem() {
        binding.answerEditText.text.clear()
        binding.operand1.text = "_"
        binding.operator.text = "_"
        binding.operand2.text = "_"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayCurrentProblem()

        if (flashcardsViewModel.isGenerateButtonDisabled) {
            disableButton(binding.generateButton)
        }
        else {
            enableButton(binding.generateButton)
        }

        if(flashcardsViewModel.displayGreeting) {
            flashcardsViewModel.updateGreeting()
            val extras = intent.extras
            if (extras != null) {
                Toast.makeText(this, "Welcome " + extras.getString("username"), Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.generateButton.setOnClickListener {
            flashcardsViewModel.updateFirstTime()
            disableButton(binding.generateButton)
            flashcardsViewModel.isGenerateButtonDisabled = true
            flashcardsViewModel.restartFlashcards()
            displayCurrentProblem()
        }

        binding.submitButton2.setOnClickListener {
            if(flashcardsViewModel.firstTime){
                Toast.makeText(this, "Generate some problems first!", Toast.LENGTH_SHORT).show()
            }
            else{
            if (!flashcardsViewModel.completedFlashcards) {
                val userAnswer = binding.answerEditText.text.toString().toInt()
                if (userAnswer == flashcardsViewModel.currentQuestionAnswer) {
                    flashcardsViewModel.incrementScore()
                }
                flashcardsViewModel.moveToNext()

                if (flashcardsViewModel.completedFlashcards) {
                    val scoreString = flashcardsViewModel.currentScore.toString() + " out of " + flashcardsViewModel.totalProblems.toString()
                    Toast.makeText(this, scoreString, Toast.LENGTH_SHORT)
                        .show()
                    displayBlankProblem()
                    enableButton(binding.generateButton)
                    flashcardsViewModel.isGenerateButtonDisabled = false
                }

                else {
                    displayCurrentProblem()
                }
            }

            else {
                val scoreString = flashcardsViewModel.currentScore.toString() + " out of " + flashcardsViewModel.totalProblems.toString()
                Toast.makeText(this@FlashCardActivity, scoreString, Toast.LENGTH_SHORT).show()
                displayBlankProblem()
            }
            }

        }
    }
}