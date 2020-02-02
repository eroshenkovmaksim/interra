package com.eroshenkov.impl

import com.eroshenkov.api.UserService
import com.eroshenkov.enum.Error.INVALID_FORMAT
import com.eroshenkov.enum.Error.UNKNOWN
import com.eroshenkov.exception.UserException
import com.eroshenkov.model.User


class UserServiceImpl : UserService {

    private val emailDictionary: MutableMap<String, String> = mutableMapOf()

    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    override fun combineUsers(inputString: String?): String {

        val userMap = inputString?.toUserList()
        try {
            userMap?.updateEmailDictionary()
            val outputString = getCombinedUserList().joinToString(separator = "\r\n", prefix = "", postfix = "")
            emailDictionary.clear()
            return outputString
        } catch (e: Exception) {
            throw UserException(UNKNOWN, e)
        }
    }

    private fun getCombinedUserList() = emailDictionary.entries.groupBy { it.value }
            .mapValues { entry -> entry.value.map { it.key }.toSet() }
            .map { User(it.key, it.value) }

    private fun List<User>.updateEmailDictionary() = this.forEach {
        val userId = getUserId(it)
        it.emails.forEach { emailDictionary[it] = userId }
    }

    private fun getUserId(user: User): String {
        val email = user.emails.firstOrNull { emailDictionary.containsKey(it) } ?: return user.userId
        return emailDictionary[email]!!
    }

    private fun String.toUserList(): List<User> {
        return try {
            trim().split("\n").map { user ->
                val (key, valueString) = user.split("->")
                val value = valueString.split(",").map { it.trim() }
                val validEmailsSet = value.validateEmails()
                User(key.trim(), validEmailsSet)
            }
        } catch (e: Exception) {
            throw UserException(INVALID_FORMAT, e)
        }
    }

    private fun List<String>.validateEmails() = this.filter { it.matches(EMAIL_REGEX.toRegex()) }.toSet()
}