package ir.snapppay.billing.controller

import ir.snapppay.billing.dto.BillDetailsDto
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import ir.snapppay.billing.mapper.BillMapper
import ir.snapppay.billing.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BillController @Autowired constructor(
    private val mapper: BillMapper,
    private val service: BillService
) {

    @GetMapping("/bill/{id}")
    fun getBill(@PathVariable id: Long): BillDetailsDto {
        val entries = service.findBillWithParticipants(id)
        val paid = entries.filterIsInstance<PaidItem>().first()
        val participants = entries.filterIsInstance<Participant>()
        return mapper.mapEntityDetails(paid, participants)
    }
}