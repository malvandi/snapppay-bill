package ir.snapppay.billing.service

import ir.bamap.blu.exception.NotFoundException
import ir.bamap.blu.model.filter.And
import ir.bamap.blu.model.filter.ClassFilter
import ir.bamap.blu.model.filter.Equal
import ir.bamap.blu.model.filter.Or
import ir.snapppay.billing.Util
import ir.snapppay.billing.config.Entities
import ir.snapppay.billing.dto.PaidRegisterDto
import ir.snapppay.billing.entity.BillComponent
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import ir.snapppay.billing.repository.BilRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BillService(
    private val repository: BilRepository
) {

    fun findBillWithParticipants(id: Long): List<BillComponent> {
        val paidFilter = And(ClassFilter(PaidItem::class.java), Equal("id", id))
        val participantsFilter = And(ClassFilter(Participant::class.java), Equal("paidId", id))
        val finalFilter = Or(paidFilter, participantsFilter)
        return repository.findBy(finalFilter)
    }

    @Transactional
    fun registerBill(dto: PaidRegisterDto) {
        val currentUsername = Util.getRandomUsername()
        val paid = PaidItem(dto.title, currentUsername, dto.amount)
        val participantUsers = dto.participants.toMutableSet()
        participantUsers.add(currentUsername)

        repository.persist(paid)
        val participantAmount = dto.amount / participantUsers.size
        participantUsers.forEach {
            val participant = Participant(paid.id, "", it, participantAmount, 0)
            repository.persist(participant)
        }
    }
}