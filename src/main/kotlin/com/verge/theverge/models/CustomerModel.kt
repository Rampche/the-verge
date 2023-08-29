package com.verge.theverge.models

import com.verge.theverge.enums.CustomerStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    var id: Int? = null,

    @Column(name= "name")
    var name: String,

    @Column(name= "email")
    var email: String,

    @JsonIgnore
    @Column(name= "password")
    var password: String,

    @Column(name= "status")
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus? = CustomerStatus.ACTIVE

)

//{
//    constructor() : this(1, "Bruna", "bruna.teste@email.com", "123456", CustomerStatus.ACTIVE)
//}