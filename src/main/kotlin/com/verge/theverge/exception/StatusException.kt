package com.verge.theverge.exception

class StatusException(override val message: String, val errorCode: String): Exception(){}