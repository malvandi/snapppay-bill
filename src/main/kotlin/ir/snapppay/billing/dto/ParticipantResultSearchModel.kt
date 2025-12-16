package ir.snapppay.billing.dto

import ir.bamap.blu.model.ResultSearchModel

class ParticipantResultSearchModel(
    val paidItems: List<PaidItemDto>,
    records: List<ParticipantDto>,
    total: Long
) : ResultSearchModel<ParticipantDto>(records, total)