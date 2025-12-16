package ir.snapppay.billing.service

import ir.bamap.blu.exception.NotFoundException
import ir.bamap.blu.model.filter.In
import ir.snapppay.billing.Util
import ir.snapppay.billing.config.Entities
import ir.snapppay.billing.exception.InsufficientBalance
import ir.snapppay.billing.entity.UserAccount
import ir.snapppay.billing.repository.UserAccountRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class UserAccountService @Autowired constructor(
    private val repository: UserAccountRepository
): CommandLineRunner {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun run(vararg args: String?) {
        try {
            val usernames = Util.usernames.toMutableSet()
            val accounts = repository.findBy(In("username", usernames))
            accounts.forEach {
                usernames.remove(it.username)
            }

            usernames.forEach {
                repository.persist(UserAccount(it, Random.nextLong(100, 200) * 1_000_000))
            }

        } catch (exception: Exception) {
            logger.error(exception.message, exception)
        }
    }

    fun transferMoney(fromUsername: String, toUsername: String, amount: Long) {
        val from = repository.findByUsernameForUpdateOrNull(fromUsername)
            ?: throw NotFoundException(Entities.USER_ACCOUNT, fromUsername)

        if(from.balance < amount)
            throw InsufficientBalance(fromUsername)

        val to = repository.findByUsernameForUpdateOrNull(toUsername)
            ?: throw NotFoundException(Entities.USER_ACCOUNT, fromUsername)

        // TODO(Register Transaction)
        to.balance += amount
        from.balance -= amount

        repository.merge(from)
        repository.merge(to)
    }
}