package glorious.church.presbyterian.glorious.repository

import glorious.church.presbyterian.glorious.util.SermonAPI

object SermonRepositoryProvider {
    fun provideSermonRepository(): SermonRepository {
        return SermonRepository(SermonAPI.createAPIService())
    }
}