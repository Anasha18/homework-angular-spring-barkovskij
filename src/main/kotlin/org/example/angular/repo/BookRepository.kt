package org.example.angular.repo

import org.example.angular.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    fun findBookById(bookId: Long): Book?

    fun findBookByTitle(title: String): Book?

    fun findBookByAuthor(author: String): Book?
}
