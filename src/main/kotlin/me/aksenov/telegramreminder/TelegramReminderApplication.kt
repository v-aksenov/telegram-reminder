package me.aksenov.telegramreminder

import me.aksenov.telegramreminder.bot.ReminderBotService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication
class TelegramReminderApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<TelegramReminderApplication>(*args)
    runApplication.getBean(ReminderBotService::class.java).start()
}
