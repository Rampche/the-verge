package com.verge.theverge.enums

enum class Errors(val code: String, val message: String) {

    //Customers Exceptions
    VG101("VG-101", "Customer nº [%s] not found"),
    VG102("VG-102", "[%s] customers not found"),
    VG103("VG-103", "Can't change customer which status is already [%s]"),
    VG104("VG-104","Unable to update a consumer with [%s] status"),

    //Tables Exceptions
    VG201("VG-201", "The table [%s] is already reserved"),
    VG202("VG-202", "Table nº [%s] not found"),

    //Employees Exceptions
    VG301("VG-301", "Employee nº [%s] not found"),
    VG302("VG-302", "[%s] employees not found"),
    VG303("VG-303", "Cant change employee [%s] status to [%s]"),
    VG304("VG-304","Unable to update a employee with [%s] status"),

    //Reservation Exceptions
    VG401("VG-401", "Reservation nº [%s] not found"),

    //Orders Exceptions
    VG501("VG-501", "Order nº [%s] not found"),

    //OrderItems Exceptions
    VG701("VG-701", "OrderItem nº [%s] not found"),

    //Items Exceptions
    VG801("VG-801", "Item nº [%s] not found")

}