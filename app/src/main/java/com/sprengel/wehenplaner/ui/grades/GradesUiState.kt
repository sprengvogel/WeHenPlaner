package com.sprengel.wehenplaner.ui.grades

data class GradesUiState(
    val rows: List<GradesRowData> = listOf()
)

data class GradesRowData(
    val course: String,
    val lp: Int,
    val grade: Double
)