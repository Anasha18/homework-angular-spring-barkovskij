@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "loans")
data class Loan(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "book_id")
    var book: Book? = null,
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id")
    var user: User? = null,
    @field:Column(name = "loan_date", nullable = false)
    var loanDate: LocalDateTime? = LocalDateTime.now(),
    @field:Column(name = "return_date")
    var returnDate: LocalDateTime? = null,
)
