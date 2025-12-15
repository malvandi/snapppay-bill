package ir.snapppay.billing.dto

data class BillDetailsDto(val paid: PaidItemDto, val participants: List<ParticipantDto>) {
}