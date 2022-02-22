package com.example.calc


class Rational() {


    private var up = 0
    private var down = 1

    init {
        up = 0
        down = 1
    }

    fun getUp(): Int {
        return up
    }

    fun getDown(): Int {
        return down
    }

    fun setUp(a: Int) {
        up = a
    }

    fun setDown(b: Int) {
        down = b
    }

    fun add(num: Rational): Rational {
        val result = Rational()
        var up2 = num.getUp()
        var down2 = num.getDown()
        if (down == down2) {
            result.up = up + up2
            result.down = down
        } else {
            up *= down2
            up2 *= down
            result.up = up + up2
            result.down = down * down2
        }
        return result
    }

    fun sub(num: Rational): Rational {
        val result = Rational()
        var up2 = num.getUp()
        var down2 = num.getDown()
        if (down == down2) {
            result.up = up - up2
            result.down = down
        } else {
            up *= down2
            up2 *= down
            result.up = up - up2
            result.down = down * down2
        }
        return result
    }

    fun mul(num: Rational): Rational {
        val result = Rational()
        var up2 = num.getUp()
        var down2 = num.getDown()
        result.up = up * up2
        result.down = down * down2
        return result
    }

    fun div(num: Rational): Rational {
        val result = Rational()
        var up2 = num.getUp()
        var down2 = num.getDown()
        result.up = up * down2
        result.down = down * up2
        return result
    }

    fun toFloatingPoint(): Double {
        return (up / down).toDouble()
    }

    override fun toString(): String {
        return ("$up / $down")
    }

    fun toMakhlotString(): String {
        val int = up / down
        var s = ""
        if (up < down){
            s = "$up / $down"
        }
        else if (up % down == 0){
            s = (up / down) .toString()
        }
        else
            s = int.toString() + " , " + (up - (int * down)).toString() + " / " + down
        return s
    }





}

fun main() {
    val a = Rational()
    a.setUp(16)
    a.setDown(5)

    // println(a.getUp())
    // println(a.getDown())

    val b = Rational()
    b.setUp(1)
    b.setDown(2)

    // println(b.getUp())
    // println(b.getDown())

    // println(a.add(b).getUp())
    // println(a.add(b).getDown())

    // println(a.sub(b).getUp())
    // println(a.sub(b).getDown())

    println(a.div(b).getUp())
    println(a.div(b).getDown())

    println(a.toMakhlotString())

}