package com.eroshenkov.enum

enum class Error(val code: String,
                 val message: String) {
    INVALID_FORMAT(code = "USER-001", message = "Invalid input data format"),
    UNKNOWN(code = "UNKNOWN", message = "Unknown exception")
}