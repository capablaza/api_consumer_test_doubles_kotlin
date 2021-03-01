package com.example.api.payment

class CreditCard {

    var name: String
    var serialNumber: Int

    constructor(name: String, serialNumber: Int) {
        this.name = name
        this.serialNumber = serialNumber
    }

    fun serialNumber(): Int {
        return this.serialNumber
    }

}