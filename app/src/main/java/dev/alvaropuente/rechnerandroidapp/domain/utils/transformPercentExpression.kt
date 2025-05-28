package dev.alvaropuente.rechnerandroidapp.domain.utils

import net.objecthunter.exp4j.ExpressionBuilder

fun transformPercentExpression(expr: String): String {
    val regex = Regex("""(.+?)\s*([\+\-\*/])\s*(\d+(?:\.\d+)?)%""")
    var match = regex.find(expr)

    var newExpr = expr
    while (match != null) {
        val before = match.groupValues[1].trim()
        val operator = match.groupValues[2]
        val percent = match.groupValues[3]

        val base = try {
            ExpressionBuilder(before).build().evaluate()
        } catch (e: Exception) {
            return expr
        }

        val replacement = when (operator) {
            "+", "-" -> "$before $operator (${base} * $percent / 100)"
            "*", "/" -> "$before $operator ($percent / 100)"
            else -> match.value
        }

        newExpr = newExpr.replaceRange(match.range, replacement)
        match = regex.find(newExpr)
    }
    return newExpr
}


