package com.example.composegoogle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composegoogle.data.DataSource.flavors
import com.example.composegoogle.data.DataSource.quantityOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

enum class CupCakeScreen(val title: Int) {
    Start(title = R.string.app_name1),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

@Composable
fun RaceTrackerApp() {
    val playerOne = remember { RaceParticipant("Minh", progressIncrement = 1)}
    val playerTwo = remember {
        RaceParticipant("Nam", progressIncrement = 2)
    }

    var raceInProgress by remember {
        mutableStateOf(false)
    }

    if(raceInProgress){
        LaunchedEffect(key1 = playerOne, key2 = playerTwo){
            coroutineScope {
                launch { playerOne.run() }
                launch { playerTwo.run() }
            }
            raceInProgress = false
        }
    }


    RaceTrackerScreen(playerOne = playerOne, playerTwo = playerTwo, isRunning = raceInProgress, onRunStateChange = {
        raceInProgress = it
    })


}

@Composable
private fun RaceTrackerScreen(
    playerOne: RaceParticipant,
    playerTwo:RaceParticipant,
    isRunning:Boolean,
    onRunStateChange:(Boolean)->Unit,
    modifier: Modifier = Modifier
){

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Run a race", modifier = modifier.padding(8.dp))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RUN",
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            StatusIndicator(
                participantName = playerOne.name,
                currentProgress = playerOne.currentProgress,
                maxProgress = "100",
                progressFactor = playerOne.progressFactor
            )
            Spacer(modifier = Modifier.size(24.dp))
            StatusIndicator(
                participantName = playerTwo.name,
                currentProgress = playerTwo.currentProgress,
                maxProgress = "200",
                progressFactor = playerTwo.progressFactor
            )
            RaceControls(
                isRunning = isRunning,
                onRunStateChange = onRunStateChange,
                onReset = {
                    playerOne.reset()
                    playerTwo.reset()
                    onRunStateChange(false)
                }
            )
        }
    }
}


@Composable
private fun StatusIndicator(
    participantName: String,
    currentProgress: Int,
    maxProgress: String,
    progressFactor: Float,
    modifier: Modifier = Modifier
) {
    Row{
        Text(participantName, Modifier.padding(end = 8.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
            LinearProgressIndicator(progress = progressFactor, modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp)))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text(maxProgress, textAlign = TextAlign.End, modifier = Modifier.weight(1f))
            }
        }

    }
}


@Composable
private fun RaceControls(
    onRunStateChange: (Boolean) -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
    isRunning: Boolean = true,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { onRunStateChange(!isRunning) }) {
            Text(if (isRunning) "pause" else "start")
        }

        Button(onClick = onReset) {
            Text("reset")
        }
    }
}


