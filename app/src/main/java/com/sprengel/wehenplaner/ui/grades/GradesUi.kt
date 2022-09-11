package com.sprengel.wehenplaner.ui.grades

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GradeList(viewModel: GradesViewModel = viewModel()) {
    val rows: List<GradesRowData> = viewModel.uiState.rows
    val cellWeight: (Int) -> Float = { index ->
        when (index) {
            0 -> 0.5f
            1 -> 0.2f
            2 -> 0.2f
            else -> 0.0f
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index -> GradeListHeader(index) }
    val cellText: @Composable (Int, GradesRowData) -> Unit = { index, row -> GradesRow(index, row) }
    Table(
        columnCount = 3,
        cellWeight = cellWeight,
        data = rows,
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}

@Composable
fun GradeListHeader(index: Int) {
    val value = when (index) {
        0 -> "Kurs"
        1 -> "LP"
        2 -> "Note"
        else -> ""
    }
    Text(
        text = value,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(16.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun GradesRow(index: Int, row: GradesRowData) {
    val value = when (index) {
        0 -> row.course
        1 -> row.lp.toString()
        2 -> row.grade.toString()
        else -> ""
    }

    Text(
        text = value,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(16.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
@Preview
fun GradeListPreview() {
    val viewModel = GradesViewModel().setGradesToExample()
    GradeList(viewModel = viewModel)
}

/**
 * The horizontally scrollable table with header and content.
 * @param columnCount the count of columns in the table
 * @param cellWeight the width of column, can be configured based on index of the column.
 * @param data the data to populate table.
 * @param modifier the modifier to apply to this layout node.
 * @param headerCellContent a block which describes the header cell content.
 * @param cellContent a block which describes the cell content.
 */
@Composable
fun <T> Table(
    columnCount: Int,
    cellWeight: (index: Int) -> Float,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            items((0..data.size).toList()) { rowIndex ->
                Row {
                    (0 until columnCount).forEach { columnIndex ->
                        Surface(
                            border = BorderStroke(
                                1.dp, Color.LightGray
                            ),
                            modifier = Modifier
                                .weight(cellWeight(columnIndex))
                        ) {
                            if (rowIndex == 0) {
                                headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[rowIndex - 1])
                            }
                        }
                    }
                }
            }
        }
    }
}