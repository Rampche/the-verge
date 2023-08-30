package com.verge.theverge.exception

class BadRequestException (override val message: String, val errorCode: String): Exception(){}