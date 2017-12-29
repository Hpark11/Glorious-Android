package glorious.church.presbyterian.glorious.util

object SermonRepositoryProvider {
    fun provideSermonRepository(): SermonRepository {
        return SermonRepository(SermonAPI.createAPIService())
    }
}