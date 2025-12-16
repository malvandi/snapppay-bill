package ir.snapppay.billing.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class UserAccount(

    val username: String = "",

    var balance: Long = 0,

    @Id
    @GeneratedValue
    val id: Long = 0,
) {
}