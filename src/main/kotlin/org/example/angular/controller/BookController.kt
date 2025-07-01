@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.controller

import org.example.angular.dto.book.request.CreateBookDto
import org.example.angular.dto.book.response.BookDto
import org.example.angular.model.Status
import org.example.angular.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(
    origins = ["*"],
    allowedHeaders = ["*"],
)
data class BookController(
    val bookService: BookService,
) {
    @PostMapping("/create")
    fun createBook(
        @RequestBody dto: CreateBookDto,
    ) {
        bookService.createBook(dto)
    }

    @PutMapping("/update/{id}")
    fun updateStatusBook(
        @PathVariable id: Long,
        @RequestBody status: Status,
    ) {
        bookService.updateStatusBook(id = id, status = status)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteBookById(
        @PathVariable id: Long,
    ) {
        bookService.deleteBookById(id)
    }

    @GetMapping("/by-title/{title}")
    fun getBookByTitle(
        @PathVariable title: String,
    ): BookDto =
        bookService.getBookByTitle(title)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Книга с таким названием не найдена")

    @GetMapping("/by-author/{author}")
    fun getBookByAuthor(
        @PathVariable author: String,
    ): BookDto? =
        bookService.getBookByAuthor(author)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Книга с таким автором не найдена")
}
