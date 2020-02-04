package com.eroshenkov

import com.eroshenkov.impl.UserServiceImpl


fun main(args: Array<String>) {
    val userService = UserServiceImpl()
    var input = ""
    while (true) {
        val newLine = readLine()
        if (newLine.isNullOrEmpty()) break
        input += "$newLine\n"
    }

    val output = userService.combineUsers(input)
    print(output)
}