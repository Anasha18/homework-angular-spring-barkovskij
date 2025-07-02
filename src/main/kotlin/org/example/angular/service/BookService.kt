package org.example.angular.service

import org.example.angular.dto.book.request.CreateBookDto
import org.example.angular.dto.book.response.BookDto
import org.example.angular.model.Book
import org.example.angular.model.Status
import org.example.angular.repo.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
data class BookService(
    val bookRepository: BookRepository,
) {
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
    fun getBookById(id: Long): Book? =
        bookRepository.findBookById(id)
            ?: throw IllegalArgumentException("Такой книги по id $id нет")

    @Transactional(readOnly = true)
    fun getBookByTitle(title: String): BookDto? {
        val bookByTitle =
            bookRepository.findBookByTitle(title)
                ?: throw IllegalArgumentException("Такой книги с именем нет $title")

        return BookDto(
            id = bookByTitle.id,
            title = bookByTitle.title,
            author = bookByTitle.author,
            isbn = bookByTitle.isbn,
            publishedDate = bookByTitle.publishedDate,
            status = bookByTitle.status,
        )
    }

    @Transactional(readOnly = true)
    fun getBookByAuthor(author: String): BookDto? {
        val bookByTitle =
            bookRepository.findBookByAuthor(author)
                ?: throw IllegalArgumentException("Такой книги у автора нет $author")

        return BookDto(
            id = bookByTitle.id,
            title = bookByTitle.title,
            author = bookByTitle.author,
            isbn = bookByTitle.isbn,
            publishedDate = bookByTitle.publishedDate,
            status = bookByTitle.status,
        )
    }

    @Transactional
    fun updateStatusBook(
        id: Long,
        status: Status,
    ) {
        val findBook = getBookById(id)

        findBook?.status = status
    }

    @Transactional
    fun deleteBookById(id: Long) {
        bookRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun getAllBooks(): List<BookDto> =
        bookRepository.findAll().map {
            BookDto(
                id = it.id,
                title = it.title,
                author = it.author,
                isbn = it.isbn,
                publishedDate = it.publishedDate,
                status = it.status,
            )
        }
}
