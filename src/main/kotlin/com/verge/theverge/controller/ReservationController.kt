package com.verge.theverge.controller

import com.verge.theverge.controller.requests.reservation.PostReservationRequest
import com.verge.theverge.controller.requests.reservation.PutReservationRequest
import com.verge.theverge.extensions.toReservationModel
import com.verge.theverge.models.ReservationModel
import com.verge.theverge.services.CustomerService
import com.verge.theverge.services.ReservationService
import com.verge.theverge.services.TablesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/reservations")
class ReservationController(
    val reservationService: ReservationService,
    val customerService: CustomerService,
    val tableService: TablesService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createReservation(@RequestBody reservation: PostReservationRequest){
        val customer = customerService.findCustomerById(reservation.customer)
        val table = tableService.findTableById(reservation.table)
        reservationService.createReservation(reservation.toReservationModel(customer, table), table)
    }

    @GetMapping
    fun findReservations(): List<ReservationModel>{
        return reservationService.findReservations()
    }

    @GetMapping("/{id}")
    fun findReservationById(@PathVariable id: Int):ReservationModel{
        return reservationService.findReservationById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateReservation(@PathVariable id: Int, @RequestBody reservation: PutReservationRequest){
            val reservationSaved = reservationService.findReservationById(id)
            val tableSaved = tableService.findTableById(reservation.table!!)
            val tableUpdated = tableService.findTableById(reservation.table)
            reservationService.updateReservation(reservation.toReservationModel(reservationSaved, tableSaved), tableUpdated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteReservation(@PathVariable id: Int){
        reservationService.deleteReservation(id)
    }
}