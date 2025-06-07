package dev.alvaropuente.rechnerandroidapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alvaropuente.rechnerandroidapp.ui.components.CalculatorButton
import dev.alvaropuente.rechnerandroidapp.ui.utils.isLandscape
import dev.alvaropuente.rechnerandroidapp.ui.utils.isTablet
import dev.alvaropuente.rechnerandroidapp.viewmodel.CalculatorViewModel

fun buttonList(): List<String> = listOf(
    "AC", "%", "C", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "00", "0", ".", "=",
)

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val isTablet = isTablet()
    val isLandscape = isLandscape()

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(modifier = modifier.padding(10.dp).fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Content
            Column(
                modifier = Modifier.weight(0.4f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                if (isTablet) {
                    if (isLandscape) {
                        Spacer(modifier = Modifier.height(20.dp))
                    } else {
                        Spacer(modifier = Modifier.height(240.dp))
                    }
                }
                Text(
                    text = equationText.value ?: "",
                    style = TextStyle(
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (isTablet) {
                    if (isLandscape) {
                        Spacer(modifier = Modifier.height(30.dp))
                    } else {
                        Spacer(modifier = Modifier.height(140.dp))
                    }
                } else {
                    Spacer(modifier = Modifier.height(140.dp))
                }
                Text(
                    text = resultText.value ?: "",
                    style = TextStyle(
                        fontSize = 60.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 2,
                )
            }
            // Bottom Content
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                if (isTablet ) {
                    if (isLandscape) {
                        items(buttonList()) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .aspectRatio(2.8f)
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CalculatorButton(
                                    btn = it,
                                    onClick = { viewModel.onButtonClick(it) })
                            }
                        }
                    } else {
                        items(buttonList()) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .aspectRatio(2f)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CalculatorButton(
                                    btn = it,
                                    onClick = { viewModel.onButtonClick(it) })
                            }
                        }
                    }
                } else {
                    if (isLandscape) {
                        items(buttonList()) {
                            CalculatorButton(
                                btn = it,
                                onClick = { viewModel.onButtonClick(it) },
                                modifier = Modifier
                                    .aspectRatio(4f)
                                    .height(5.dp)
                                    .width(1.dp)
                                    .padding(8.dp)
                            )
                        }
                    } else {
                        items(buttonList()) {
                            CalculatorButton(
                                btn = it,
                                onClick = { viewModel.onButtonClick(it) },
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}