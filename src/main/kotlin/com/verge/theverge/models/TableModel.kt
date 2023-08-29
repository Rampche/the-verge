package com.verge.theverge.models

import com.verge.theverge.enums.TableStatus
import jakarta.persistence.*

@Entity(name="tables")
data class TableModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "tables_id")
    var id: Int? = null,

    @Column
    var number: Int,

    @Column
    var capacity: Int,

    @Column
    @Enumerated(EnumType.STRING)
    var status: TableStatus? = TableStatus.EMPTY

)
