package ir.snapppay.billing.repository

import ir.bamap.blu.jpa.repository.BluRepositoryImpl
import ir.bamap.blu.model.filter.Equal
import ir.snapppay.billing.entity.UserAccount
import jakarta.persistence.LockModeType
import org.springframework.stereotype.Repository

@Repository
class UserAccountRepository : BluRepositoryImpl<UserAccount, Long>(UserAccount::class.java) {

    fun findByUsernameForUpdateOrNull(username: String): UserAccount? {
        val filters = listOf(Equal("username", username))

        val query= createQuery(UserAccount::class.java, filters, emptyList())
        val typedQuery = entityManager.createQuery(query)
        typedQuery.lockMode = LockModeType.PESSIMISTIC_WRITE
        typedQuery.maxResults = 1

        return typedQuery.resultList
            .firstOrNull()
    }
}