package com.hzcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = "Happy Kirby"
        val contentDescription = "Kirby Image"

        setContent {
            val painter = painterResource(id = R.drawable.kirby_two)

            LazyList()


        }
    }

    @Composable
    private fun LazyList() {
        LazyColumn(modifier = Modifier.background(Color.White)) {
            itemsIndexed(
                listOf(
                    "Hi",
                    "This",
                    "is",
                    "Compose",
                    "List",
                    "sample",
                    "with",
                    "lists"
                )
            ) { _, item ->
                Text(
                    text = item,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )

            }
        }
    }

    @Composable
    private fun SimpleListWithCount() {
        LazyColumn(modifier = Modifier.background(Color.White)) {
            items(500) {
                Text(
                    text = "Item $it",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }

    @Composable
    private fun SnackBar() {
        val scaffoldState = rememberScaffoldState()
        var textFieldValue by remember {
            mutableStateOf("")
        }
        val scope = rememberCoroutineScope()

        Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {

                OutlinedTextField(
                    value = textFieldValue,
                    label = {
                        "Enter Text"
                    },
                    onValueChange = {
                        textFieldValue = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldValue")
                    }
                }) {
                    Text(text = "Click")
                }

            }

        }
    }

    @Composable
    fun ColorBox(
        modifier: Modifier = Modifier,
        updatedColor: (Color) -> Unit
    ) {
        Box(modifier = modifier
            .background(Color.Red)
            .clickable {
                updatedColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )

            }) {

        }

    }


    @Composable
    fun ImageCard(
        title: String,
        painter: Painter,
        contentDescription: String,
        modifier: Modifier = Modifier
    ) {

        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            elevation = 8.dp
        ) {

            Box(modifier = modifier.height(300.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 700f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(title, style = TextStyle(Color.White, fontSize = 16.sp))
                }


            }


        }


    }

    @Composable
    fun StateExample() {
        Column(Modifier.fillMaxSize()) {
            val color = remember {
                mutableStateOf(Color.Yellow)
            }
            ColorBox(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                color.value = it
            }
            Box(
                modifier = Modifier
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize()
            )


        }
    }

    @Composable
    fun CardViewExample(
        title: String,
        painter: Painter,
        contentDescription: String
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            ImageCard(title = title, painter = painter, contentDescription = contentDescription)

        }
    }


}


