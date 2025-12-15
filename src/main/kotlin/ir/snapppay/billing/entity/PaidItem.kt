package ir.snapppay.billing.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("paidItem")
class PaidItem(
    val title: String = "",
    username: String = "",
    amount: Long,
    id: Long = 0
): BillComponent(username, amount, id)