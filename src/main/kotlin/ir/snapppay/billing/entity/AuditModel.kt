package ir.snapppay.billing.entity

import ir.snapppay.billing.Util
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.util.*

@Embeddable
class AuditModel(
    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    val createdBy: String = Util.getCurrentUsername(),

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    val createdAt: Date = Date(),

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", nullable = false)
    var modifiedBy: String = Util.getCurrentUsername(),

    @LastModifiedDate
    @Column(name = "MODIFIED_AT", nullable = false)
    var modifiedAt: Date = Date()
) : Serializable