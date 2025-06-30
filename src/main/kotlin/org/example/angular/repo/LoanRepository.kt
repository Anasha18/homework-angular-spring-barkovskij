package org.example.angular.repo

import org.example.angular.model.Loan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : JpaRepository<Loan, Long> {
    @Query(
        """
            select l from Loan l
            join fetch l.user
            join fetch l.book
            where l.id = :loanId
        """,
    )
    fun findLoanById(loanId: Long): Loan?

    @Query(
        """
            select l from Loan l
            join fetch l.user
            join fetch l.book
        """,
    )
    fun findAllLoans(): List<Loan>
}
