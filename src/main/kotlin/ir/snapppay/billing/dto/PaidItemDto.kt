package ir.snapppay.billing.dto

import ir.snapppay.billing.entity.AuditModel

data class PaidItemDto(
    val title: String = "",
    override val username: String = "",
    override val amount: Long,
    override val audit: AuditModel = AuditModel(),
    val id: Long = 0
) : BillComponentDto(username, amount, audit) {
}