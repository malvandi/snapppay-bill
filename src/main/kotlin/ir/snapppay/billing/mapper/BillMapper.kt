package ir.snapppay.billing.mapper

import ir.bamap.blu.exception.NotSupportedTypeException
import ir.bamap.blu.mapper.SimpleBluMapper
import ir.snapppay.billing.dto.BillComponentDto
import ir.snapppay.billing.dto.BillDetailsDto
import ir.snapppay.billing.dto.PaidItemDto
import ir.snapppay.billing.dto.ParticipantDto
import ir.snapppay.billing.entity.BillComponent
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import org.springframework.stereotype.Service

@Service
class BillMapper : SimpleBluMapper<BillComponent, BillComponentDto>(BillComponent::class, BillComponentDto::class) {

    override fun mapEntity(entity: BillComponent): BillComponentDto {
        return when (entity) {
            is PaidItem -> map(entity, PaidItemDto::class)
            is Participant -> map(entity, ParticipantDto::class)
            else -> throw NotSupportedTypeException(entity::class.java, "MAP_ENTITY_TO_DTO")
        }
    }

    fun mapEntityDetails(paidItem: PaidItem, participants: List<Participant>): BillDetailsDto {
        return BillDetailsDto(
            mapEntity(paidItem) as PaidItemDto,
            mapEntities(participants).filterIsInstance<ParticipantDto>()
        )
    }
}