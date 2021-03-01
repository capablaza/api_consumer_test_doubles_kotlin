package com.example.api.payment

interface PaymentRestClient {
    fun sendPayment(request: PaymentRequest): PaymentResponse

}