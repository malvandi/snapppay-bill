package ir.snapppay.billing.repository

import ir.bamap.blu.jpa.repository.BluRepositoryImpl
import ir.snapppay.billing.entity.UserAccount
import org.springframework.stereotype.Repository

@Repository
class UserAccountRepository : BluRepositoryImpl<UserAccount, Long>(UserAccount::class.java) {
}