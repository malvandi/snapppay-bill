package ir.snapppay.billing.dto

import ir.bamap.blu.model.CustomSearchModel
import ir.bamap.blu.model.SearchModel
import ir.bamap.blu.model.filter.ClassFilter
import ir.bamap.blu.model.filter.Equal
import ir.bamap.blu.model.filter.GreaterThan
import ir.bamap.blu.model.filter.Like
import ir.snapppay.billing.entity.PaidItem
import ir.snapppay.billing.entity.Participant
import ir.snapppay.billing.enums.BillType

data class BillingSearch(
    val currentUser: String = "",
    override val page: Int = 0,
    override val limit: Int = 20,
) : CustomSearchModel(page, limit) {

    override fun toSearchModel(): SearchModel {
        val searchModel = SearchModel(page, limit)

        if (currentUser.isNotEmpty())
            searchModel.filter(Equal("username", currentUser))

        return searchModel
    }
}