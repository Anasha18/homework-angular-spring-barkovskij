package org.example.angular.service

import org.example.angular.dto.loan.request.CreateLoanDto
import org.example.angular.dto.loan.response.LoanDto
import org.example.angular.model.Loan
import org.example.angular.repo.BookRepository
import org.example.angular.repo.LoanRepository
import org.example.angular.repo.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
data class LoanService(
    val loanRepository: LoanRepository,
    val userRepository: UserRepository,
    val bookRepository: BookRepository,
) {
    fun getLoanById(id: Long): Loan? =
        loanRepository.findLoanById(id)
            ?: throw IllegalArgumentException("Такой записи по id: $id нет")

    @Transactional
    fun createLoan(dto: CreateLoanDto) {
        val bookId =
            bookRepository.findBookById(dto.bookId)
                ?: throw IllegalArgumentException("Книги с таким id: ${dto.bookId} нет")

        val userId =
            userRepository.findUserById(dto.userId)
                ?: throw IllegalArgumentException("Пользователя с таким id: ${dto.userId} нет")

        loanRepository.save(
            Loan(
                book = bookId,
                user = userId,
                loanDate = LocalDateTime.now(),
                returnDate = dto.returnDate,
            ),
        )
    }

    @Transactional(readOnly = true)
    fun getAllLoans(): List<LoanDto> =
        loanRepository.findAll().map { loan ->
            LoanDto(
                id = loan.id,
                bookId = loan.book?.id,
                bookTitle = loan.book?.title,
                userId = loan.user?.id,
                userFullName = loan.user?.fullName,
                loanDate = loan.loanDate,
                returnDate = loan.returnDate,
            )
        }

    @Transactional
    fun updateReturnDateLoanById(
        id: Long,
        returnDate: LocalDateTime?,
    ) {
        val findLoan = getLoanById(id)

        findLoan?.returnDate = returnDate
    }

    @Transactional
    fun deleteLoanById(id: Long) {
        val findLoan = getLoanById(id)

        loanRepository.delete(findLoan!!)
    }
}
