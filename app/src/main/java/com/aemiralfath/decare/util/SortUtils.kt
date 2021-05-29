package com.aemiralfath.decare.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    private const val OLDEST = "Oldest"
    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM reminderentity ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY id DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY id ASC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}