package com.github.cawboyroy.dicerollerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.github.cawboyroy.dicerollerapp.ui.theme.DiceRollerAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = (application as App).viewModel
        val lifecycleOwner = this

        enableEdgeToEdge()
        setContent {
            DiceRollerAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)) {
                    DiceRollerApp(lifecycleOwner, viewModel)
                }
            }
        }
    }
}

@Composable
fun DiceRollerApp(
    lifecycleOwner: LifecycleOwner,
    viewModel: MainViewModel,
    modifier: Modifier) {
    DiceWithButtonAndImage(lifecycleOwner, viewModel)
}

@Composable
fun DiceWithButtonAndImage(
    lifecycleOwner: LifecycleOwner,
    viewModel: MainViewModel,
    modifier: Modifier) {

    var resourceId by remember {mutableStateOf(R.drawable.dice_1)}

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(resourceId),
            contentDescription = "image"
        )
        Spacer(modifier.height(16.dp))
        Button(modifier = modifier,
            onClick = { viewModel.changePicture()}) {
                Text(stringResource(R.string.roll))
        }
    }

    viewModel.observe(lifecycleOwner) {
        resourceId = it
    }
}

@Preview
@Composable ()
fun DiceRollerApp() {

}