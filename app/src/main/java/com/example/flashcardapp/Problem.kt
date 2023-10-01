package com.example.flashcardapp

data class Problem(
    val firstOperand: Int,
    val operator: Int,
    val secondOperand: Int
)
{
    val answer = if (operator == 0) firstOperand + secondOperand else firstOperand - secondOperand
}
