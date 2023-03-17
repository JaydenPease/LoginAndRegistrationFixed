package com.example.databases

import java.util.*

data class Loan(
    var name: String = "",
    var loanAmount: Double = 0.0,
    var reasonForLoan: String = "",
    var dateOfLoan: Date = Date(0),
    var amountRepaid: Double = 0.0,
    var dateOfFullRepayment: Date? = null,
    var fullyRepaid: Boolean = false
) {
    fun amountNotRepaid() : Double {
        return loanAmount - amountRepaid
    }
}
