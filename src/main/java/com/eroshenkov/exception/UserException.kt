package com.eroshenkov.exception

import com.eroshenkov.enum.Error

class UserException(error: Error, e: Throwable) : Exception(error.message, e)