package com.verge.theverge.services.seed


import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.models.ItemsModel
import com.verge.theverge.repository.CustomerRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CustomerSeedService @Autowired constructor (
    private val customerRepository: CustomerRepository
){
    @PostConstruct
    fun seedCustomers() {
        val customers = listOf(

            CustomerModel(id = 1, name = "Pedro Almeida", email = "pedro.almeida@gmail.com", password = "1234qwe", status = CustomerStatus.ACTIVE),
            CustomerModel(id = 2, name = "Bruna Freitas", email = "bruna.freitas@gmail.com", password = "zxccxchjh", status = CustomerStatus.ACTIVE),
            CustomerModel(id = 3, name = "José Nunes", email = "jose.nunes@gmail.com", password = "8ss565215", status = CustomerStatus.ACTIVE),

        )
        customerRepository.saveAll(customers)
    }
}