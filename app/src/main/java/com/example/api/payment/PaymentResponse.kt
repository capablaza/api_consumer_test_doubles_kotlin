package com.example.api.payment

class PaymentResponse {
    var code = 0

    constructor(code: Int) {
        this.code = code
    }

    fun code(): Int {
        return this.code
    }
}