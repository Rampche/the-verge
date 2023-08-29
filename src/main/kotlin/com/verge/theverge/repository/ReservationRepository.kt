package com.verge.theverge.repository

import com.verge.theverge.models.ReservationModel
import org.springframework.data.repository.CrudRepository

interface ReservationRepository:CrudRepository <ReservationModel, Int> {
}