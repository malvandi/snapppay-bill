package ir.snapppay.billing.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ir.snapppay.billing.entity.AuditModel
import ir.snapppay.billing.entity.Participant
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "dtype",
    defaultImpl = Participant::class
)
@JsonSubTypes(
    JsonSubTypes.Type(value = PaidItemDto::class, name = "paid"),
    JsonSubTypes.Type(value = ParticipantDto::class, name = "participant")
)
abstract class BillComponentDto(
    open val username: String = "",
    open val amount: Long = 0,

    open val audit: AuditModel = AuditModel()
) {
}