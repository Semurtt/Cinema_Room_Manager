fun parseCardNumber(cardNumber: String): Long {
    val (a, b, c, d) = cardNumber.split(" ")
    val card = a + b + c + d
    return if (a.length != 4 || b.length != 4 || c.length != 4 || d.length != 4) {
        throw Exception("number is incorrect")
    } else card.toLong()
}