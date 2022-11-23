package com.example.composegoogle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composegoogle.ui.theme.ComposeGoogleTheme

class MainActivity : ComponentActivity() {
    var a = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var name by delegateObject
        setContent {
            ComposeGoogleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    BirthdayGreetingWithText(message = "Minh1")
                    LemonApp(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }

    @Composable
    fun LemonApp(modifier: Modifier = Modifier) {
        // Current step the app is displaying (remember allows the state to be retained
        // across recompositions).
        var currentStep by remember { mutableStateOf(1) }
        var amountInput by remember {
            mutableStateOf("")
        }
        val amount = amountInput.toDoubleOrNull() ?: 0.0

        Log.e(this.javaClass.simpleName, "LemonApp")

        // A surface container using the 'background' color from the theme
        Column(verticalArrangement = Arrangement.Top) {
            Surface(
                modifier = modifier,
                color = MaterialTheme.colorScheme.error
            ) {
                Log.e(this.javaClass.simpleName, "LemonApp")
                EditNumberField(amountInput, onValueChange = { amountInput = it })
            }

        }
    }

    @Composable
    fun EditNumberField(value: String, onValueChange: (String) -> Unit) {
        BasicTextField(value = value, onValueChange = onValueChange)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        LemonApp()
    }
}


