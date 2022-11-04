package me.aksenov.telegramreminder.bot

import me.aksenov.telegramreminder.configuration.TelegramProperties
import me.aksenov.telegramreminder.logger.Logger
import me.aksenov.telegramreminder.service.ReminderService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import javax.annotation.PostConstruct

@Service
class ReminderBotService(
    private val telegramProperties: TelegramProperties,
    private val reminderService: ReminderService
) : TelegramLongPollingBot(), Logger {

    override fun getBotToken(): String = telegramProperties.token

    override fun getBotUsername(): String = telegramProperties.username

    override fun onUpdateReceived(update: Update?) {
        update?.message?.text?.let {
            val chatId = update.message.chat.id
            val response = reminderService.parseAndSaveReminder(update.message.chat.id, it)
            sendMessage(response, chatId)
        }
    }

    @PostConstruct
    fun start() {
        TelegramBotsApi(DefaultBotSession::class.java).registerBot(this)
        log.info("telegram bot started")
    }

    fun sendMessage(message: String, chatId: Long) {
        execute(SendMessage(chatId.toString(), message))
    }
}
