package com.example.api.payment

interface Storage {
    fun save(request: PaymentRequest)
    fun find(sale: Sale): PaymentRequest
    fun remove(request: PaymentRequest)
}