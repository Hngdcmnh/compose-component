package com.example.composegoogle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun SelectOptionScreen(
    options: List<String>,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    var selectedValue by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier.padding(10.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(selected = selectedValue == item, onClick = {
                    selectedValue = item
                })
            ) {
                RadioButton(selected = selectedValue == item, onClick = { selectedValue = item })
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }

    }

}