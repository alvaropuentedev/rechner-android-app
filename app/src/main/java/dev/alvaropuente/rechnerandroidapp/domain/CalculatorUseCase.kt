package dev.alvaropuente.rechnerandroidapp.domain

import net.objecthunter.exp4j.ExpressionBuilder

fun calculatorUseCase(expression: String, resultText: String): String {
    if (!isExpressionSafe(expression)) return resultText
    return try {
        val result = ExpressionBuilder(expression).build().evaluate()
        if (result == result.toLong().toDouble())
            result.toLong().toString()
        else
            result.toString()
    } catch (e: Exception) {
        "Error"
    }
}
/**
 * Checks if a given mathematical expression is safe to evaluate.
 *
 * An expression is considered safe if it's not empty and its last character is a digit or a closing parenthesis.
 * This helps prevent evaluation errors if the expression ends with an operator.
 *
 * @param expr The mathematical expression string to check.
 * @return `true` if the expression is considered safe, `false` otherwise.
 */
fun isExpressionSafe(expr: String): Boolean {
    return expr.isNotEmpty() &&
            expr.last() in '0'..'9' || expr.last() == ')'
}