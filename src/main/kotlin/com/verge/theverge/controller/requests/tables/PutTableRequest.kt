package com.verge.theverge.controller.requests.tables

import com.verge.theverge.enums.TableStatus

data class PutTableRequest(
    val id: Int?,
    val number: Int?,
    val capacity: Int?,
    val status: TableStatus?
)
