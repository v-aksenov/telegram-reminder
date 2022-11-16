package me.aksenov.telegramreminder.controller

import me.aksenov.telegramreminder.service.ReminderService
import me.aksenov.telegramreminder.service.model.CreateReminderRequest
import me.aksenov.telegramreminder.service.model.Reminder
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reminder")
class ReminderController(private val reminderService: ReminderService) {

    @PostMapping
    fun create(@RequestBody reminderRequest: CreateReminderRequest): Reminder =
        reminderService.saveReminder(reminderRequest.toReminder())

    @DeleteMapping
    fun delete(@RequestParam id: ObjectId): Unit = reminderService.removeReminder(id)

    @GetMapping
    fun get(@RequestParam chatId: Long): List<Reminder> =
        reminderService.getReminders(chatId)
}
