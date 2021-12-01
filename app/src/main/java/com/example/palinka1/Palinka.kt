package com.example.palinka1

class Palinka {
    var id = 0
    var fozo = ""
    var gyumolcs = ""
    var alkohol = 0

    constructor(fozo :String, gyumolcs :String, alkohol :Int) {
        this.fozo = fozo
        this.gyumolcs = gyumolcs
        this.alkohol = alkohol
    }

    constructor(id :Int, fozo :String, gyumolcs :String, alkohol :Int) {
        this.id = id
        this.fozo = fozo
        this.gyumolcs = gyumolcs
        this.alkohol = alkohol
    }

    override fun toString(): String {
        return String.format("%-20s %-15s %d", this.fozo, this.gyumolcs, this.alkohol)
    }
}