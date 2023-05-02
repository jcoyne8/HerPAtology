package com.jcoynemobile.herpatology

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val yesArray: IntArray, val noArray: IntArray)