package cinema

import java.math.RoundingMode

var rows = 0
var seats = 0
var room = MutableList(rows) { MutableList(seats) { 'S' } }
var tickets = 0
var percentage = 0.0
var income = 0
var total = 0


fun main() {
    println("Enter the number of rows:")
    rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    seats = readLine()!!.toInt()
    room = MutableList(rows) { MutableList(seats) { 'S' } }
    do {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val n = readLine()!!.toInt()
        if (n == 1) cinema()
        if (n == 2) buyTicket()
        if (n == 3) statistics()
    } while (n != 0)
}


fun cinema() {
    var temp = 1
    println()
    print("Cinema:\n ")
    for (i in 1..seats) {
        print(" $i")
    }
    println()
    for (lists in room) {
        print("$temp ")
        println(lists.joinToString(" "))
        temp++
    }
}

fun buyTicket() {
    println()
    var rowNumber: Int
    var seatNumber: Int
    var quit = 0
    do {
        println("Enter a row number:")
        rowNumber = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        seatNumber = readLine()!!.toInt()
        when {
            rowNumber > rows -> println("Wrong input! Enter different seat coordinates")
            seatNumber > seats -> println("Wrong input! Enter different seat coordinates")
            room[rowNumber - 1][seatNumber - 1] == 'B' -> println("That ticket has already been purchased! Enter different seat coordinates")
            else -> quit = 1
        }
    } while (quit != 1)

    if (rows * seats <= 60) {
        println("Ticket price: \$10")
        income += 10
    } else {
        if (rowNumber <= rows / 2) {
            println("Ticket price: \$10")
            income += 10
        }
        if (rowNumber > rows / 2) {
            println("Ticket price: \$8")
            income += 8
        }
    }
    room[rowNumber - 1][seatNumber - 1] = 'B'
    tickets++
}

fun statistics() {
    if (rows * seats <= 60) {
        total = rows * seats * 10
    } else
        if (rows % 2 == 0) total = rows / 2 * seats * 10 + rows / 2 * seats * 8
    if (rows % 2 == 1) total = rows / 2 * seats * 10 + (rows / 2 + 1) * seats * 8
    percentage = (100/(rows.toDouble()* seats)* tickets)
    println()
    println("Number of purchased tickets: $tickets")
    println("Percentage: ${percentage.toBigDecimal().setScale(2, RoundingMode.HALF_UP)}%")
    println("Current income: $$income")
    println("Total income: $$total")
}