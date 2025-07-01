package org.example.angular.dto.loan.request

import java.time.LocalDateTime

data class CreateLoanDto(
    val bookId: Long,
    val userId: Long,
    val returnDate: LocalDateTime,
)
