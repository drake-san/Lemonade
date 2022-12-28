package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeWithTextAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithTextAndImage(modifier: Modifier = Modifier) {
    var stateOfPreparation by remember {
        mutableStateOf(1)
    }

    var squeezeCounter by remember {
        mutableStateOf(0)
    }

    val imageResource = when(stateOfPreparation) {
        1 -> {
            squeezeCounter = (1..4).random()
            R.drawable.lemon_tree
        }
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    
    val textDescription = when(imageResource) {
        R.drawable.lemon_tree -> stringResource(id = R.string.lemon_tree)
        R.drawable.lemon_squeeze -> stringResource(id = R.string.lemon)
        R.drawable.lemon_drink -> stringResource(id = R.string.glass_of_lemonade)
        else -> stringResource(id = R.string.empty_glass)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = textDescription,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color(102, 205, 216), RoundedCornerShape(5.dp))
                .clickable {
                    if (stateOfPreparation == 2) {
                        squeezeCounter--
                        if (squeezeCounter == 0) {
                            stateOfPreparation = 3
                        }
                    }
                    else {
                        ++stateOfPreparation
                        if (stateOfPreparation >= 4)
                            stateOfPreparation = 0
                    }

                }
        )

    }
}