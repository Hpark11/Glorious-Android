package glorious.church.presbyterian.glorious.repository

object SermonRepositoryProvider {
    fun provideSermonRepository(): SermonRepository {
        return SermonRepository(SermonAPI.createAPIService())
    }
}