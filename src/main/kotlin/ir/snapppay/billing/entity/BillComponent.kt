package ir.snapppay.billing.entity

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "bill")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener::class)
abstract class BillComponent(
    val username: String = "",
    val amount: Long = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
) {
    @Embedded
    val audit: AuditModel = AuditModel()
}
