package com.example.composegoogle

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun StartOrderScreen(
    quantityOptions: List<Pair<Int, Int>>,
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = null,
            modifier = Modifier.width(300.dp)
        )
        quantityOptions.forEach { item ->
            SelectQuantityButton(onClick = {
                onNextButtonClicked(item.second)
            }, labelResourceId = item.first)
        }

    }
}

@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = onClick, modifier = modifier.widthIn(min = 250.dp)) {
        Text(stringResource(id = labelResourceId))
    }
}