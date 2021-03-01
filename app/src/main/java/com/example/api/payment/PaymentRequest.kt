package com.example.api.payment

class PaymentRequest {
    var sale: Sale
    var creditCard: CreditCard

    constructor(sale: Sale, card: CreditCard) {
        this.creditCard = card
        this.sale = sale
    }

    fun sale() : Sale{
        return this.sale
    }

    fun isFill(): Boolean {
        return sale != null && creditCard != null
    }

}