package ir.snapppay.billing.service

import ir.bamap.blu.exception.NotFoundException
import ir.bamap.blu.model.filter.And
import ir.bamap.blu.model.filter.ClassFilter
import ir.bamap.blu.model.filter.Equal
import ir.bamap.blu.model.filter.Or
import ir.snapppay.billing.config.Entities
import ir.snapppay.billing.dto.BillingSearch
import ir.snapppay.billing.dto.PaidItemDto
import ir.snapppay.billing.dto.PaidRegisterDto
import ir.snapppay.billing.dto.ParticipantDto
import ir.snapppay.billing.dto.ParticipantResultSearchModel
import ir.snapppay.billing.entity.BillComponent
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import ir.snapppay.billing.mapper.BillMapper
import ir.snapppay.billing.repository.BillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BillService(
    private val mapper: BillMapper,
    private val repository: BillRepository,
    private val userAccountService: UserAccountService
) {

    @Transactional
    fun pay(username: String, billId: Long) {
        val paidItem = repository.findOrNull(PaidItem::class.java, billId)
            ?: throw NotFoundException(Entities.PAID, billId)

        val participant = repository.findParticipantForUpdate(billId, username)
            ?: throw NotFoundException(Entities.PARTICIPANT, "${billId}_$username")

        userAccountService.transferMoney(participant.username, paidItem.username, participant.amount)

        participant.paymentId = "PAID"
        repository.merge(participant)
    }

    fun search(searchModel: BillingSearch): ParticipantResultSearchModel {
        val participants = repository.findBy(Participant::class.java, searchModel)
        val paidIds = participants.records.map { it.paidId }

        val paidItemsDto = repository.findByIds(paidIds)
            .map { mapper.mapEntity(it) }
            .filterIsInstance<PaidItemDto>()

        val participantsDto = participants.records.map { mapper.mapEntity(it) }
            .filterIsInstance<ParticipantDto>()

        return ParticipantResultSearchModel(paidItemsDto, participantsDto, participants.total )
    }

    fun findBillWithParticipants(id: Long): List<BillComponent> {
        val paidFilter = And(ClassFilter(PaidItem::class.java), Equal("id", id))
        val participantsFilter = And(ClassFilter(Participant::class.java), Equal("paidId", id))
        val finalFilter = Or(paidFilter, participantsFilter)
        return repository.findBy(finalFilter)
    }

    @Transactional
    fun registerBill(currentUsername: String, dto: PaidRegisterDto): List<BillComponent> {
        val total = mutableListOf<BillComponent>()
        val paid = PaidItem(dto.title, currentUsername, dto.amount)
        total.add(paid)

        val participantUsers = dto.participants.toMutableSet()
        participantUsers.add(currentUsername)

        repository.persist(paid)
        val participantAmount = dto.amount / participantUsers.size
        participantUsers.forEach {
            val participant = Participant(paid.id, "", it, participantAmount, 0)
            repository.persist(participant)
            total.add(participant)
        }

        return total
    }
}