package com.example.choosey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.choosey.ui.theme.ChooseyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChooseyTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Choosey(
                        title = "CHOOSEY",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Choosey(title: String, modifier: Modifier = Modifier) {
    Column ( modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
             verticalArrangement = Arrangement.Center
        )
    {
        Box ( modifier = Modifier
                .weight(1f),

        )
        {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 75.sp,
                lineHeight = 116.sp,
                textAlign = TextAlign.Center,
            )
        }
        Box (
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        )
        {
            Button(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .height(250.dp)
                    .width(250.dp)
                    .shadow(
                        elevation = 50.dp,
                        shape = CircleShape,
                        ambientColor = Color.Red, // glow color
                        spotColor = Color.Red
                    ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 50.dp,
                    pressedElevation = 30.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000000),
                    contentColor = Color.White
                ),

                shape = RoundedCornerShape(250.dp),

                onClick =
                    { println("Button Clicked!") }
            )
            {
                Text("Help Me Choosey",
                    modifier = Modifier,
                    fontSize = 30.sp,
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),)
    }
}

@Preview(showBackground = true, )
@Composable
fun CHOOSEYPreview() {
    ChooseyTheme {
        Choosey("CHOOSEY")
    }
}