package com.sprengel.wehenplaner.ui.grades

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GradesViewModel : ViewModel() {

    var uiState by mutableStateOf(GradesUiState())
        private set

    fun setGradesToExample(): GradesViewModel {
        val gradeRows = mutableListOf(
            GradesRowData(course = "TestCourse", lp = 10, grade = 1.0),
            GradesRowData(course = "TestCourse2", lp = 6, grade = 1.3)
        )
        for (i in 1..15) {
            gradeRows.add(GradesRowData(course = "TestCourse2", lp = 6, grade = 1.3))
        }
        uiState = uiState.copy(rows = gradeRows.toList())
        return this
    }
}