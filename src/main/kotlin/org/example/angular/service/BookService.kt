package org.example.angular.service

import org.example.angular.dto.book.CreateBookDto
import org.example.angular.dto.book.UpdateStatusBookDto
import org.example.angular.model.Book
import org.example.angular.repo.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
data class BookService(
    val bookRepository: BookRepository,
) {
    @Transactional(readOnly = true)
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    @Transactional
    fun createBook(dto: CreateBookDto): Book? =
        bookRepository.save(
            Book(
                title = dto.title,
                author = dto.author,
                isbn = dto.isbn,
                publishedDate = dto.publishedDate,
                status = dto.status,
            ),
        )

    @Transactional(readOnly = true)
    fun getBookById(id: Long): Book? = bookRepository.findBookById(id)

    @Transactional(readOnly = true)
    fun getBookByIsbn(isbn: String): List<Book> = bookRepository.findBookByIsbn(isbn)

    @Transactional(readOnly = true)
    fun getBookByTitle(title: String): List<Book> = bookRepository.findBookByTitle(title)

    @Transactional(readOnly = true)
    fun getBookByAuthor(author: String): List<Book> = bookRepository.findBookByAuthor(author)

    @Transactional
    fun updateStatusBook(dto: UpdateStatusBookDto): Book? {
        val bookId = dto.bookId ?: return null

        val findBook =
            bookRepository.findBookById(bookId) ?: throw IllegalArgumentException("No book found for id: $bookId")

        findBook.status = dto.status

        return findBook
    }

    @Transactional
    fun deleteBookById(bookId: Long): Book? {
        val findBook =
            bookRepository.findBookById(bookId)
                ?: throw IllegalArgumentException("No book found for id: $bookId")

        return bookRepository.deleteBookById(bookId)
    }
}
