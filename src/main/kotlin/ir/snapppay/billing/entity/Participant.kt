package ir.snapppay.billing.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("participant")
class Participant(
    val paidId: Long = 0,
    var paymentId: String = "",
    username: String = "",
    amount: Long,
    id: Long = 0
): BillComponent(username, amount, id)