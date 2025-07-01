package org.example.angular.repo

import org.example.angular.model.Loan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : JpaRepository<Loan, Long> {
    fun findLoanById(loanId: Long): Loan?
}
