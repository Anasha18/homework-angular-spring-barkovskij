@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.controller

import org.example.angular.dto.book.CreateBookDto
import org.example.angular.dto.book.UpdateStatusBookDto
import org.example.angular.model.Book
import org.example.angular.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
data class BookController(
    val bookService: BookService,
) {
    @PostMapping("/createBook")
    fun createBook(
        @RequestBody dto: CreateBookDto,
    ): Book? = bookService.createBook(dto)

    @GetMapping("/book/{isbn}")
    fun getBookByIsbn(
        @PathVariable isbn: String,
    ): List<Book>? = bookService.getBookByIsbn(isbn)

    @GetMapping("/book/{title}")
    fun getBookByTitle(
        @PathVariable title: String,
    ): List<Book>? = bookService.getBookByTitle(title)

    @GetMapping("/book/{author}")
    fun getBookByAuthor(
        @PathVariable author: String,
    ): List<Book> = bookService.getBookByAuthor(author)

    @PutMapping("/book/update")
    fun updateStatusBook(dto: UpdateStatusBookDto): Book? = bookService.updateStatusBook(dto)

    @DeleteMapping("/book/{id}")
    fun deleteBookById(
        @PathVariable id: Long,
    ) = bookService.deleteBookById(id)
}
