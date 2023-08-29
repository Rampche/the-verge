package com.verge.theverge.exception

class ReservedTableException(override val message: String, val errorCode: String): Exception(){}
