package com.example.api.payment

class StorageDummy : Storage {
    override fun save(request: PaymentRequest) {

    }

    override fun find(sale: Sale): PaymentRequest {
        return PaymentRequest(Sale(), CreditCard("", 0))
    }

    override fun remove(request: PaymentRequest) {

    }
}