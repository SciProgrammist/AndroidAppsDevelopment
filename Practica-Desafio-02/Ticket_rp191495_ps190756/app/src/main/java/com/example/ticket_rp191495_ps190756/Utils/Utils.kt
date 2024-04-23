package com.example.ticket_rp191495_ps190756.Utils

import java.util.Random

class Utils {
    fun generarNumeroTicket(): String {
        val longitudNumeroTicket = 8
        val caracteresPermitidos = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val random = Random()
        val buffer = StringBuilder(longitudNumeroTicket)

        for (i in 0 until longitudNumeroTicket) {
            buffer.append(caracteresPermitidos[random.nextInt(caracteresPermitidos.length)])
        }

        return buffer.toString()
    }
}