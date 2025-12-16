package ir.snapppay.billing.exception

import ir.bamap.blu.exception.BluException

class InsufficientBalance(username: String) :
    BluException(402, "{user.insufficient_balance}", mapOf("username" to username)) {
}