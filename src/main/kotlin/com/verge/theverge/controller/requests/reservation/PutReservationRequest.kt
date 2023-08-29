package com.verge.theverge.controller.requests.reservation


import com.fasterxml.jackson.annotation.JsonAlias
import java.time.LocalDate
import java.time.LocalTime

data class PutReservationRequest (
    val date: LocalDate?,
    val time: LocalTime?,
    val partySize: Int?,
    @JsonAlias("customer_id")
    val customer: Int?,
    @JsonAlias("table_id")
    val table: Int?
)