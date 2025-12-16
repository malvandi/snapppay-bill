package ir.snapppay.billing.repository

import ir.bamap.blu.jpa.repository.BluRepositoryImpl
import ir.bamap.blu.model.filter.Equal
import ir.snapppay.billing.entity.BillComponent
import ir.snapppay.billing.entity.Participant
import jakarta.persistence.LockModeType
import jakarta.persistence.criteria.Root
import org.springframework.stereotype.Repository

@Repository
class BillRepository: BluRepositoryImpl<BillComponent, Long>(BillComponent::class.java) {

    fun findParticipantForUpdate(paidId: Long, username: String): Participant?  {
        val filters = listOf(Equal("username", username), Equal("paidId", paidId))

        val query= createQuery(Participant::class.java, filters, emptyList())
        val typedQuery = entityManager.createQuery(query)
        typedQuery.lockMode = LockModeType.PESSIMISTIC_WRITE
        typedQuery.maxResults = 1

        return typedQuery.resultList
            .firstOrNull()
    }
}