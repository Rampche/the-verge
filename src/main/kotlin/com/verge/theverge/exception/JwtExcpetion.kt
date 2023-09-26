package com.verge.theverge.exception

class JwtExcpetion(override val message: String, val errorCode: String): Exception(){}