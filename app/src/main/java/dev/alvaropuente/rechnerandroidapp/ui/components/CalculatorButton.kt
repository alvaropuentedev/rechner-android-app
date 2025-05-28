package dev.alvaropuente.rechnerandroidapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = getColor(btn),
            onClick = onClick
        ) {
            Text(
                text = btn,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun getColor(btn: String): Color {
    return when (btn) {
        "AC", "C", "(", ")", "/", "*", "-", "+" -> Color(0xFF9E9E9E)
        "=" -> Color(0xFFFF8A80)
        else -> Color(0xFFF2F2F2)
    }
}