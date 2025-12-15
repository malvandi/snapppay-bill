package ir.snapppay.billing.dto

class PaidRegisterDto(
    val title: String = "",
    val amount: Long = 0L,
    val participants: List<String> = emptyList(),
)
