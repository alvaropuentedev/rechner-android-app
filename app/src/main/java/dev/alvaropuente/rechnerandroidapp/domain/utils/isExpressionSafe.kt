package dev.alvaropuente.rechnerandroidapp.domain.utils

/**
 * An expression is considered safe if it's not empty and its last character is a digit or a closing parenthesis.
 * This helps prevent evaluation errors if the expression ends with an operator.
 */
fun isExpressionSafe(expr: String): Boolean {
    return expr.isNotEmpty() &&
            expr.last() in '0'..'9' || expr.last() == '%'
}