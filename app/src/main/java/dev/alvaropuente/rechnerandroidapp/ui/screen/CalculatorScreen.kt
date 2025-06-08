package dev.alvaropuente.rechnerandroidapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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

    val buttonModifier = remember(isTablet, isLandscape) {
        when {
            isTablet && isLandscape -> Modifier
                .height(100.dp)
                .aspectRatio(2.8f)
                .padding(12.dp)

            isTablet && !isLandscape -> Modifier
                .height(100.dp)
                .aspectRatio(2f)
                .padding(8.dp)

            !isTablet && isLandscape -> Modifier
                .aspectRatio(4f)
                .padding(8.dp)

            else -> Modifier
                .aspectRatio(1f)
                .padding(8.dp)
        }
    }

    Box(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
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
                val spacerHeight = when {
                    isTablet && isLandscape -> 20.dp
                    isTablet -> 240.dp
                    else -> 0.dp
                }
                Spacer(modifier = Modifier.height(spacerHeight))
                Text(
                    text = equationText.value ?: "",
                    style = TextStyle(
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                val resultSpacerHeight = when {
                    isTablet && isLandscape -> 30.dp
                    isTablet -> 140.dp
                    else -> 140.dp
                }
                Spacer(modifier = Modifier.height(resultSpacerHeight))
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
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(buttonList()) { button ->
                    CalculatorButton(
                        btn = button,
                        onClick = { viewModel.onButtonClick(button) },
                        modifier = buttonModifier
                    )
                }
            }
        }
    }
}