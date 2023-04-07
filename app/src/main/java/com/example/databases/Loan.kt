package com.example.databases

import java.util.*
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Loan(
    var name: String = "",
    var loanAmount: Double = 0.0,
    var reasonForLoan: String = "",
    var dateOfLoan: Date = Date(0),
    var amountRepaid: Double = 0.0,
    var dateOfFullRepayment: Date? = null,
    var fullyRepaid: Boolean = false,
    var ownerId: String? = null,
    var objectId: String? = null
) : Parcelable {
    fun amountNotRepaid() : Double {
        return loanAmount - amountRepaid
    }
}
