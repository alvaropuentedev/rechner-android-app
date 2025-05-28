package dev.alvaropuente.rechnerandroidapp.domain.useCase

import dev.alvaropuente.rechnerandroidapp.domain.utils.isExpressionSafe
import dev.alvaropuente.rechnerandroidapp.domain.utils.transformPercentExpression
import net.objecthunter.exp4j.ExpressionBuilder

fun calculatorUseCase(expression: String, resultText: String): String {
    if (!isExpressionSafe(expression)) return resultText
    val transformed = transformPercentExpression(expression)
    return try {
        val result = ExpressionBuilder(transformed).build().evaluate()
        if (result == result.toLong().toDouble())
            result.toLong().toString()
        else
            result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

