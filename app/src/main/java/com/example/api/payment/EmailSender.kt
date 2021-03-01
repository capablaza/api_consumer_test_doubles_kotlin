package com.example.api.payment

interface EmailSender {
    fun sendEmail(paymentRequest: PaymentRequest)
}