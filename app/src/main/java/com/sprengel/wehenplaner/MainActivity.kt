package com.sprengel.wehenplaner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sprengel.wehenplaner.ui.grades.GradeList
import com.sprengel.wehenplaner.ui.grades.GradesViewModel
import com.sprengel.wehenplaner.ui.theme.WeHenPlanerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gradesViewModel: GradesViewModel by viewModels()
        gradesViewModel.setGradesToExample()
        setContent {
            WeHenPlanerTheme {
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Text(
                                text = "WeHen-Planer",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(start = 12.dp),
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* ... */ }) {
                            Icon(Icons.Filled.Add, "")
                        }
                    }
                ) { contentPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(contentPadding),
                        color = MaterialTheme.colors.background
                    ) {
                        GradeList()
                    }
                }
            }
        }
    }
}

