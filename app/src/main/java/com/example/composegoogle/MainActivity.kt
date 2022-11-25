package com.example.composegoogle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composegoogle.data.DataSource
import com.example.composegoogle.model.ItemData
import com.example.composegoogle.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    var a = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var name by delegateObject
        setContent {
            TestTheme {
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

        DataList(data = DataSource().loadData())
    }
    
    @Composable
    fun DataList(data:List<ItemData>, modifier: Modifier = Modifier) {

        Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
            Text(text = "1",)
            Text(text = "2",)
            Text(text = "3",)
            Text(text = "4",)
            Text(text = "5", modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        Pair(0f, Color.Blue), Pair(1f, Color.Red)
                    )
                ), textAlign = TextAlign.Start, )
        }
//        LazyHorizontalGrid(rows = GridCells.Fixed(2), content = {
//            item(content = {
//                Image(painter = painterResource(id = R.drawable.image2), contentDescription = "")
//            })
//            item(content = {
//                Image(painter = painterResource(id = R.drawable.image3), contentDescription = "")
//            })
//            item(content = {
//                Image(painter = painterResource(id = R.drawable.image4), contentDescription = "")
//            })
//        })
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


