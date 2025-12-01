package com.example.Exercise_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.Exercise_7.ui.CounterRow
import com.example.Exercise_7.ui.theme.MultiCounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiCounterAppTheme {
                // Background surface using Material3
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MultiCounterScreen()
                }
            }
        }
    }
}

@Composable
fun MultiCounterScreen() {
    // Create a list of 20 counters (each with its own independent state) to demonstrate scrolling
    val counterStates = remember { (1..20).map { mutableStateOf(0) } }

    // LazyColumn for efficient scrollable list
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)  // Space between items
    ) {
        itemsIndexed(counterStates) { index, countState ->
            // Render each counter row with independent increment/decrement
            CounterRow(
                name = "Counter_${index + 1}",
                count = countState.value,
                onIncrement = { countState.value++ },
                onDecrement = { countState.value-- }
            )
        }
    }
}