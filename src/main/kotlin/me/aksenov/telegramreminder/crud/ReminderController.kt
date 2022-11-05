package me.aksenov.telegramreminder.crud

import me.aksenov.telegramreminder.storage.ReminderRepository
import me.aksenov.telegramreminder.storage.model.Reminder
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/reminder")
class ReminderController(private val reminderRepository: ReminderRepository) {

    @PostMapping
    fun create(@RequestBody reminder: Reminder): Reminder = reminderRepository.save(reminder)

    @DeleteMapping
    fun delete(@RequestParam id: ObjectId): Unit = reminderRepository.deleteById(id)

    @GetMapping
    fun get(@RequestParam(required = false) chatId: Long?): List<Reminder> =
        if (chatId == null) {
            reminderRepository.findByProcessedIsFalse()
        } else {
            reminderRepository.findByChatIdAndProcessedIsFalse(chatId)
        }
}
