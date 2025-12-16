package ir.snapppay.billing.controller

import ir.snapppay.billing.dto.BillDetailsDto
import ir.snapppay.billing.dto.BillingSearch
import ir.snapppay.billing.dto.PaidRegisterDto
import ir.snapppay.billing.dto.ParticipantResultSearchModel
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import ir.snapppay.billing.mapper.BillMapper
import ir.snapppay.billing.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    private val billMapper: BillMapper,
    private val billService: BillService
) {

    @PostMapping("/{username}/bill")
    fun addBill(@PathVariable username: String, @RequestBody paid: PaidRegisterDto): BillDetailsDto {
        val result = billService.registerBill(username, paid)

        val paid = result.filterIsInstance<PaidItem>().first()
        val participants = result.filterIsInstance<Participant>()
        return billMapper.mapEntityDetails(paid, participants)
    }

    @PostMapping("/{username}/billings")
    fun findBillings(@PathVariable username: String, @RequestBody search: BillingSearch): ParticipantResultSearchModel {
        val validSearch = search.copy(currentUser = username)
        return billService.search(validSearch)
    }

    @PostMapping("/{username}/bill/{id}/pay")
    fun payBill(@PathVariable username: String, @PathVariable id: Long) {
        billService.pay(username, id)
    }
}