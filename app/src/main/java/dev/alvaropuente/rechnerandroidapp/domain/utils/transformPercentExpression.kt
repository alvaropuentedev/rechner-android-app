package dev.alvaropuente.rechnerandroidapp.domain.utils

fun transformPercentExpression(expr: String): String {
    val regex = Regex("""(\d+(?:\.\d+)?)\s*([\+\-\*\/])\s*(\d+(?:\.\d+)?)%""")

    var newExpr = expr
    var match = regex.find(newExpr)

    while (match != null) {
        val base = match.groupValues[1]
        val operator = match.groupValues[2]
        val percent = match.groupValues[3]
        val replacement = when (operator) {
            "+", "-" -> "$base $operator ($base * $percent / 100)"
            "*", "/" -> "$base $operator ($percent / 100)"
            else -> match.value
        }
        newExpr = newExpr.replaceRange(match.range, replacement)
        match = regex.find(newExpr)
    }
    return newExpr
}