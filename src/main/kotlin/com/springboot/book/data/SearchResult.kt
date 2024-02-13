package com.springboot.book.data

data class SearchResult(
    val kind: String,
    val etag: String,
    val id: SearchId,
    val snippet: SearchSnippet,
)
