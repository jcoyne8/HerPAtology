package com.jcoynemobile.herpatology

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    //bank links to strings, and has arrays that correspond with indices of database to increment an array on the fragment
    private val questionBank = listOf(
        Question(R.string.quiz_question_1, intArrayOf(3,4,5,6,10,11,12,13,15,19,20), intArrayOf(0,1,2,4,6,7,8,9,13,14,15,16,17,18)),
        Question(R.string.quiz_question_2, intArrayOf(0,1,2,7), intArrayOf(3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20)),
        Question(R.string.quiz_question_3, intArrayOf(4,6,8,10,11,15,16,17), intArrayOf(0,1,2,3,4,5,6,7,8,9,12,13,14,15,16,17,18,19,20)),
        Question(R.string.quiz_question_4, intArrayOf(10,13,14,15,16,17,18), intArrayOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,18,19,20)),
        Question(R.string.quiz_question_5, intArrayOf(0,1,2), intArrayOf(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)),
        Question(R.string.quiz_question_6, intArrayOf(1,2,3,4,5,6,7,9,12,15,18,20), intArrayOf(0,1,3,4,7,8,9,10,11,13,14,15,16,17,18,19,20)),
        Question(R.string.quiz_question_7, intArrayOf(3,5,6,10,11,13,15,16,17), intArrayOf(0,1,2,4,5,7,8,9,12,13,14,15,16,18,19,20)),
        Question(R.string.quiz_question_8, intArrayOf(1,5,8,9,12), intArrayOf()),
        Question(R.string.quiz_question_9, intArrayOf(9,13,17), intArrayOf()),
        Question(R.string.quiz_question_10, intArrayOf(0,1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,20), intArrayOf(10,11,13,17)),
        Question(R.string.quiz_question_11, intArrayOf(0,2,4,5,6,8,9,11,12,13,14,15,16,17,18), intArrayOf(0,1,3,5,6,7,8,9,11,12,13,14,15,16,17,18,19,20)),
        Question(R.string.quiz_question_12, intArrayOf(0,1,3,5,6,7,8,9,11,12,14,15,17,18), intArrayOf()),
        Question(R.string.quiz_question_13, intArrayOf(1,5,6,8,9,11,12,14,15,16,17,18,19), intArrayOf()),
        Question(R.string.quiz_question_14, intArrayOf(0,1,3,5,6,7,8,9,10,11,12,13,14,15,17,18,19,20), intArrayOf(0,1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19))
    )

    var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentYesArray: IntArray
        get() = questionBank[currentIndex].yesArray

    val currentNoArray: IntArray
        get() = questionBank[currentIndex].noArray

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}