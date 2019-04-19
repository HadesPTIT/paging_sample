package com.hades.codelabs.paging

import android.arch.lifecycle.ViewModelProvider
import com.hades.codelabs.paging.api.GithubApi
import com.hades.codelabs.paging.api.GithubApiService
import com.hades.codelabs.paging.data.GithubRepository
import com.hades.codelabs.paging.data.InMemoryByPageKeyRepository
import com.hades.codelabs.paging.ui.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    /**
     * Creates an instance of [GithubRepository] based on the [GithubApiService]
     */
    private fun provideGithubRepository(): GithubRepository {
        return InMemoryByPageKeyRepository(provideGithubApiService(), NETWORK_IO)
    }

    /**
     * Creates an instance of [GithubApiService] based on the [GithubApi]
     */
    private fun provideGithubApiService(): GithubApiService {
        return GithubApiService(GithubApi.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}