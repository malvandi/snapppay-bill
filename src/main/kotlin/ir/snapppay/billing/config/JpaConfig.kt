package ir.snapppay.billing.config

import ir.snapppay.billing.Util
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware {
            Optional.of(Util.getCurrentUsername())
        }
    }
}