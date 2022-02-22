package com.example.calc

class Calculator() {
    private var p:Float = 0F
    fun plus(a: Float, b: Float): Float {
        p = a + b
        return p
    }
    fun plus(a: Float): Float {
        p += a
        return p
    }

    fun mines(a: Float, b: Float): Float {
        p = a - b
        return p
    }
    fun mines(a: Float): Float {
        p -= a
        return p
    }

    fun multi(a: Float, b: Float): Float{
        p = a * b
        return p
    }
    fun multi(a: Float): Float {
        p *= a
        return p
    }


    fun division(a: Float, b: Float): Float {
        try {
            p = a / b
            return p
        }
        catch (e:Exception){
            return (0F)
        }
    }
    fun division(a: Float) : Float {
        try {
            p /= a
            return p
        }
        catch (e:Exception){
            return 0F
        }

    }
}
fun main(){

    val c = Calculator()
    c.plus(5F, 6F)
    c.plus(5F)
    c.mines(12F)
    c.division(0F)
}

