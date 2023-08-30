package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.enums.TableStatus
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.exception.ReservedTableException
import com.verge.theverge.models.ReservationModel
import com.verge.theverge.models.TableModel
import com.verge.theverge.repository.ReservationRepository
import com.verge.theverge.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(
    val reservationRepository: ReservationRepository,
    val tableRepository: TableRepository
    ) {

    //Create a reservation
    fun createReservation(reservation: ReservationModel, table: TableModel?){
        reservationRepository.save(reservation)
        if (table != null && table.status == TableStatus.EMPTY) {
            table.status = TableStatus.RESERVED
        tableRepository.save(table)
        }
        val customer = reservation.customer
        if (customer != null){
            customer.id = reservation.id
        }
    }

    //List the reservations
    fun findReservations(): List<ReservationModel>{
        return reservationRepository.findAll().toList()
    }

    //List reservations by id
    fun findReservationById(id: Int):ReservationModel{
        return reservationRepository.findById(id).orElseThrow { NotFoundException(Errors.VG401.message.format(id), Errors.VG401.code) }
    }

    //Update the reservation
    fun updateReservation(reservation: ReservationModel, tableUpdated: TableModel?){
        if (!reservationRepository.existsById(reservation.id!!)){
            throw NotFoundException(Errors.VG401.message.format(reservation.id), Errors.VG401.code)
        }
        if (tableUpdated != null && tableUpdated.status == TableStatus.EMPTY) {
            tableUpdated.status = TableStatus.RESERVED
            tableRepository.save(tableUpdated)
        } else if (tableUpdated != null && tableUpdated.status == TableStatus.RESERVED){
            throw ReservedTableException(Errors.VG201.message.format(tableUpdated.id), Errors.VG201.code)
        }
        reservationRepository.save(reservation)
    }

    //Delete the reservation
    fun deleteReservation(id: Int){
        if (!reservationRepository.existsById(id)){
          throw NotFoundException(Errors.VG401.message.format(id), Errors.VG401.code)
        }
        val reserve = reservationRepository.findById(id)
        val table = reserve.get().table
        if (table != null && table.status == TableStatus.RESERVED) {
            table.status = TableStatus.EMPTY
            tableRepository.save(table)
        }
        reservationRepository.deleteById(id)
    }

}