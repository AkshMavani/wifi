package com.example.demo_wifi

import java.util.*

class Pwdgenerate {
     fun generateRandomPassword(max_length: Int, upperCase: Boolean, lowerCase: Boolean, numbers: Boolean, specialCharacters: Boolean): String? {
        val upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lowerCaseChars = "abcdefghijklmnopqrstuvwxyz"
        val numberChars = "0123456789"
        val specialChars = "!@#$%^&*()_-+=<>?/{}~|"
        var allowedChars = ""
        val rn = Random()
        val sb = StringBuilder(max_length)

        if (upperCase) {
            allowedChars += upperCaseChars
            sb.append(upperCaseChars[rn.nextInt(upperCaseChars.length)])
        }
        if (lowerCase) {
            allowedChars += lowerCaseChars
            sb.append(lowerCaseChars[rn.nextInt(lowerCaseChars.length)])
        }
        if (numbers) {
            allowedChars += numberChars
            sb.append(numberChars[rn.nextInt(numberChars.length)])
        }
        if (specialCharacters) {
            allowedChars += specialChars
            sb.append(specialChars[rn.nextInt(specialChars.length )])
        }
        for (i in sb.length until max_length) {
            sb.append(allowedChars[rn.nextInt(allowedChars.length)])
        }
        return sb.toString()
    }
}