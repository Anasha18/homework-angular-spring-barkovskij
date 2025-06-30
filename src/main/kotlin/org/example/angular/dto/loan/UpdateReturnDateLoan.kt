package org.example.angular.dto.loan

import java.time.LocalDateTime

data class UpdateReturnDateLoan(
    val loanId: Long?,
    val returnDate: LocalDateTime?,
)
