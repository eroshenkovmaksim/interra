package com.eroshenkov.service

import com.eroshenkov.exception.UserException
import com.eroshenkov.impl.UserServiceImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFailsWith


class UserServiceImplTest {

    private val userService = UserServiceImpl()

    private val WITH_COMBINING = "/withCombining.txt"
    private val WITH_COMBINING_ANSWER = "/withCombiningAnswer.txt"

    private val WITHOUT_COMBINING = "/withoutCombining.txt"
    private val WITHOUT_COMBINING_ANSWER = "/withoutCombiningAnswer.txt"

    private val WITH_INVALID_EMAILS = "/withInvalidEmails.txt"
    private val WITH_INVALID_EMAILS_ANSWER = "/withInvalidEmailsAnswer.txt"

    @Test
    fun combineUsersTestCombining() {
        val testData = this::class.java.getResource(WITH_COMBINING).readText()
        val combinedUsers = userService.combineUsers(testData)
        val testDataResponse = this::class.java.getResource(WITH_COMBINING_ANSWER).readText()
        assertTrue(combinedUsers.isNotEmpty())
        assertEquals(combinedUsers, testDataResponse)
    }

    @Test
    fun combineUsersTest() {
        val testData = this::class.java.getResource(WITHOUT_COMBINING).readText()
        val combinedUsers = userService.combineUsers(testData)
        val testDataResponse = this::class.java.getResource(WITHOUT_COMBINING_ANSWER).readText()
        assertTrue(combinedUsers.isNotEmpty())
        assertEquals(combinedUsers, testDataResponse)
    }

    @Test
    fun combineUsersTestWithInvalidEmails() {
        val testData = this::class.java.getResource(WITH_INVALID_EMAILS).readText()
        val combinedUsers = userService.combineUsers(testData)
        val testDataResponse = this::class.java.getResource(WITH_INVALID_EMAILS_ANSWER).readText()
        assertTrue(combinedUsers.isNotEmpty())
        assertEquals(combinedUsers, testDataResponse)
    }

    @Test
    fun combineUsersExceptionTest() {
        assertFailsWith(UserException::class) { userService.combineUsers("invalid format") }
    }
}