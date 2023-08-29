package com.verge.theverge.exception

class NotFoundException(override val message: String, val errorCode: String): Exception(){
}
