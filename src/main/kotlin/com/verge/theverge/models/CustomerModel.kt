package com.verge.theverge.models

import com.verge.theverge.enums.CustomerStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadRequestException
import com.verge.theverge.exception.StatusException
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
    var password: String

    //? = CustomerStatus.ACTIVE
){
    @Column(name= "status")
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus ? = null
        set(value) {
            if (field == CustomerStatus.DELETED){
                throw BadRequestException(Errors.VG103.message.format(field), Errors.VG103.code)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        email: String,
        password: String,
        status: CustomerStatus?
    ): this(id, name, email, password){
        this.status = status
    }
}

//{
//    constructor() : this(1, "Bruna", "bruna.teste@email.com", "123456", CustomerStatus.ACTIVE)
//}