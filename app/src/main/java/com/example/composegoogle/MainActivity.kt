package com.example.composegoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegoogle.data.DataSource
import com.example.composegoogle.model.ItemData
import com.example.composegoogle.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    var a = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//  var name by delegateObject
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
        DataList(data = DataSource().loadData())
    }

    @Composable
    fun DataList(data: List<ItemData>, modifier: Modifier = Modifier) {

//        Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
//            Text(text = "1")
//            Text(text = "2")
//            Text(text = "3")
//            Text(text = "4")
//            Text(
//                text = "5",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            Pair(0f, Color.Blue), Pair(1f, Color.Red)
//                        )
//                    ),
//                textAlign = TextAlign.Start,
//            )
//        }

        LazyColumn(content = {
            items(data) {
                ItemDog(item = it)
            }

        })
    }

    @Composable
    fun ItemDog(item: ItemData) {
        var expanded by remember {
            mutableStateOf(false)
        }

        Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    DogIcon(item.image)
                    DogInformation(item.title, item.title)
                    Spacer(Modifier.weight(1f))
                    DogItemButton(
                        expanded = expanded,
                        onClick = { expanded = !expanded },
                    )
                }
                if (expanded) {
                    DogHobby(item.title)
                }

            }
        }
    }

    @Composable
    private fun DogItemButton(
        expanded: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        IconButton(onClick =  onClick ) {
            Icon(imageVector = if (expanded) ImageVector.vectorResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24) else ImageVector.vectorResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24) , contentDescription = "")

//            Icon(
//                imageVector = if (expanded) Icons.Filled else Icons.Filled,
//                tint = MaterialTheme.colorScheme.secondary
//            )
        }
//        IconButton(onClick = onClick) {
//            Icon(
//                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
//                tint = MaterialTheme.colors.secondary,
//                contentDescription = stringResource(R.string.expand_button_content_description),
//            )
//        }
    }

    @Composable
    fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
        Image(
            modifier = modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop,
            painter = painterResource(dogIcon),
            /*
             * Content Description is not needed here - image is decorative, and setting a null content
             * description allows accessibility services to skip this element during navigation.
             */
            contentDescription = null
        )
    }

    @Composable
    fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
        Column {
            Text(
                text = stringResource(dogName),
                modifier = modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.title, dogAge),
            )
        }
    }

    /**
     * Composable that displays a dog's hobbies.
     *
     * @param dogHobby is the resource ID for the text string of the hobby to display
     * @param modifier modifiers to set to this composable
     */
    @Composable
    fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.padding(
                start = 16.dp,
                top = 8.dp,
                bottom = 16.dp,
                end = 16.dp
            )
        ) {
            Text(
                text = stringResource(R.string.click),
            )
            Text(
                text = stringResource(dogHobby),
            )
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


