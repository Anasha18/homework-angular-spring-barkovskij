package org.example.angular.controller

import org.example.angular.dto.loan.request.CreateLoanDto
import org.example.angular.dto.loan.response.LoanDto
import org.example.angular.service.LoanService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/loans")
@CrossOrigin(
    origins = ["*"],
    allowedHeaders = ["*"],
)
data class LoanController(
    val loanService: LoanService,
) {
    @PostMapping("/create")
    fun createLoan(
        @RequestBody dto: CreateLoanDto,
    ) = loanService.createLoan(dto = dto)

    @PutMapping("/update/{id}")
    fun updateReturnDateLoan(
        @RequestBody returnDate: LocalDateTime,
        @PathVariable id: Long,
    ) = loanService.updateReturnDateLoanById(id = id, returnDate = returnDate)

    @GetMapping
    fun getLoans(): List<LoanDto> = loanService.getAllLoans()

    @DeleteMapping("/delete/{id}")
    fun deleteLoanById(
        @PathVariable id: Long,
    ) {
        loanService.deleteLoanById(id = id)
    }
}
