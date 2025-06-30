package org.example.angular.service

import org.example.angular.dto.loan.CreateLoanDto
import org.example.angular.dto.loan.UpdateReturnDateLoan
import org.example.angular.model.Loan
import org.example.angular.repo.LoanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
data class LoanService(
    val loanRepository: LoanRepository,
) {
    @Transactional
    fun createLoan(dto: CreateLoanDto): Loan? =
        loanRepository.save(
            Loan(
                book = dto.book,
                user = dto.user,
                loanDate = LocalDateTime.now(),
                returnDate = dto.returnDate,
            ),
        )

    @Transactional
    fun updateReturnDateLoan(dto: UpdateReturnDateLoan): Loan? {
        val loanId = dto.loanId ?: return null

        val findLoan =
            loanRepository.findLoanById(loanId) ?: throw IllegalArgumentException("No book found for id: $loanId")

        findLoan?.returnDate = dto.returnDate
        return findLoan
    }

    @Transactional(readOnly = true)
    fun getAllLoans(): List<Loan> = loanRepository.findAllLoans()
}
