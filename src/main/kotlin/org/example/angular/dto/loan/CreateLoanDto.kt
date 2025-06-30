package org.example.angular.dto.loan

import org.example.angular.model.Book
import org.example.angular.model.User
import java.time.LocalDateTime

data class CreateLoanDto(
    val book: Book?,
    val user: User?,
    val loanDate: LocalDateTime? = LocalDateTime.now(),
    val returnDate: LocalDateTime?,
)
