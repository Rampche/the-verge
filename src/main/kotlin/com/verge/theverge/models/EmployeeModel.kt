package com.verge.theverge.models


import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.Errors
import com.verge.theverge.enums.RoleType
import com.verge.theverge.exception.StatusException
import jakarta.persistence.*

@Entity(name = "employee")
data class EmployeeModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    var id: Int? = null,

    @Column(name="name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleType::class, fetch = FetchType.EAGER)
    @CollectionTable(name="roles", joinColumns = [JoinColumn(name= "employee_id")])
    var role: Set<RoleType> = setOf(RoleType.COMMON),

    @Column(name = "password")
    var password: String

) {
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    var status: EmployeeStatus ? = EmployeeStatus.ACTIVE
        set(value) {
            if (field == EmployeeStatus.DISMISSED){
                throw StatusException(Errors.VG103.message.format(field), Errors.VG103.code)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        email: String,
        role: Set<RoleType>,
        password: String,
        status: EmployeeStatus?
    ): this(id, name, email, role, password){
        this.status = status
    }
}
