package com.hades.codelabs.paging.data

import com.hades.codelabs.paging.api.Item

/**
 * @author chetansachdeva
 */

interface GithubRepository {
    fun searchUsers(searchQuery: String, pageSize: Int): Listing<Item>
}