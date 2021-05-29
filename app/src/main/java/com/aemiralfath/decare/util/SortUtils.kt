package com.aemiralfath.decare.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM reminderentity ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY time DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}