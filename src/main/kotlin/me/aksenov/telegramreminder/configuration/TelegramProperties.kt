package me.aksenov.telegramreminder.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties
data class TelegramProperties(
    val token: String,
    val username: String
)
