package com.verge.theverge.exception

class BadLoginException(override val message: String, val errorCode: String): Exception(){}