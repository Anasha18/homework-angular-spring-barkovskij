package org.example.angular.dto.loan.response

import java.time.LocalDateTime

data class LoanDto(
    val id: Long?,
    val bookId: Long?,
    val bookTitle: String?,
    val userId: Long?,
    val userFullName: String?,
    val loanDate: LocalDateTime?,
    val returnDate: LocalDateTime?,
)
