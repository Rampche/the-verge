package com.verge.theverge.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalTime

@Entity(name = "orders")
data class OrderModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "orders_id")
    var id: Int? = null,

    @Column(name ="schedule")
    var schedule: LocalTime,

    @ManyToOne
    @JoinColumn(name = "employee_id")
    var employee: EmployeeModel? = null,

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    var reservation: ReservationModel? = null

)
