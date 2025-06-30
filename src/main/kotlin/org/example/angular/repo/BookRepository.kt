package org.example.angular.repo

import org.example.angular.model.Book
import org.example.angular.model.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    @Query(
        """
            select b from Book b
            join fetch b.loans
        """,
    )
    fun findAllBooks(): List<Book>

    @Query(
        """
            select b from Book b
            join fetch b.loans
            where b.id = :bookId
        """,
    )
    fun findBookById(bookId: Long): Book?

    @Modifying
    @Query(
        """
        update Book b
        set b.title = :title,
            b.isbn = :isbn,
            b.publishedDate = :publishedDate,
            b.status = :status
        where b.id = :bookId
    """,
    )
    fun updateBookById(
        bookId: Long?,
        title: String?,
        isbn: String?,
        publishedDate: LocalDateTime?,
        status: Status?,
    ): Book // todo

    fun deleteBookById(bookId: Long): Book?

    @Query(
        """
        select b from Book b
        join fetch b.loans
        where b.isbn = :isbn
    """,
    )
    fun findBookByIsbn(isbn: String): List<Book>

    @Query(
        """
        select b from Book b
        join fetch b.loans
        where b.title = :title
    """,
    )
    fun findBookByTitle(title: String): List<Book>

    @Query(
        """
        select b from Book b
        join fetch b.loans
        where b.author = :author
    """,
    )
    fun findBookByAuthor(author: String): List<Book>
}
