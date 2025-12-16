package ir.snapppay.billing.controller

import ir.snapppay.billing.dto.BillingSearch
import ir.snapppay.billing.dto.ParticipantResultSearchModel
import ir.snapppay.billing.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    private val billService: BillService
) {

    @PostMapping("/{username}/billings")
    fun findBillings(@PathVariable username: String, @RequestBody search: BillingSearch): ParticipantResultSearchModel {
        val validSearch = search.copy(currentUser = username)
        return billService.search(validSearch)
    }
}