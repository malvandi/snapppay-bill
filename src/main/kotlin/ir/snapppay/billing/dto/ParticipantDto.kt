package ir.snapppay.billing.dto

import ir.snapppay.billing.entity.AuditModel

data class ParticipantDto(
    val paidId: Long = 0,
    val paymentId: String = "",
    val isPaid: Boolean = paymentId.isNotEmpty(),
    override val username: String = "",
    override val amount: Long,
    override val audit: AuditModel = AuditModel()
) : BillComponentDto(username, amount, audit)
