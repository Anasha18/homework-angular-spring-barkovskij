@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

@Entity
@Table(name = "books")
data class Book(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:Column(name = "title", length = 255, nullable = false)
    var title: String? = null,
    @field:Column(name = "author", length = 255, nullable = false)
    var author: String? = null,
    @field:Column(name = "isbn", length = 255, nullable = false)
    var isbn: String? = null,
    @field:Column(name = "published_date", nullable = false)
    var publishedDate: LocalDateTime? = null,
    @field:Enumerated(EnumType.STRING)
    @field:JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @field:Column(columnDefinition = "status")
    var status: Status? = Status.AVAILABLE,
    @field:OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var loans: Set<Loan> = emptySet(),
)
