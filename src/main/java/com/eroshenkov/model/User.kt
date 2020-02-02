package com.eroshenkov.model

class User(val userId: String, val emails: Set<String>) {
    override fun toString(): String {
        return "$userId -> ${emails.joinToString(separator = ", ", prefix = "", postfix = "")}"
    }
}