package me.aksenov.telegramreminder.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.aksenov.telegramreminder.bot.ReminderBotService
import me.aksenov.telegramreminder.service.model.CreateReminderRequest
import me.aksenov.telegramreminder.service.model.Reminder
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class ReminderControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) : StringSpec() {

    @MockkBean(relaxed = true)
    private lateinit var reminderBotService: ReminderBotService

    init {
        "create reminder" {
            val reminder = post("/reminder", reminderRequest)
            reminder.chatId shouldBe reminderRequest.chatId
            reminder.description shouldBe reminderRequest.description
            reminder.minutes shouldBe reminderRequest.minutes
            reminder.id shouldNotBe null
        }
    }

    private fun post(uri: String, request: CreateReminderRequest): Reminder =
        mockMvc.post(urlTemplate = uri) {
            content = objectMapper.writeValueAsString(request)
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andReturn()
            .response
            .contentAsString
            .let { objectMapper.readValue(it, Reminder::class.java) }

    private val reminderRequest = CreateReminderRequest(
        chatId = 123L,
        description = "test",
        minutes = 1,
        hours = 0
    )
}
