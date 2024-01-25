import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean

interface VideoService

data class YoutubeService(val name: String = "YouTube") : VideoService
data class VimeoService(val name: String = "Vimeo") : VideoService