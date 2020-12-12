package rationals

import java.math.BigInteger


class Rational(numerator: BigInteger, denominator: BigInteger?) : Comparable<Rational>{
    private val n = numerator
    private var d = denominator ?: 1L.toBigInteger()

    operator fun plus(rational: Rational): Rational {
        val a = this.n * rational.d
        val b = this.d * rational.d
        val c = rational.n * this.d
        return Rational(a + c, b)
    }
    operator fun times(rational: Rational): Rational {
        val a = this.n
        val b = this.d
        val c = rational.n
        val d = rational.d
        return Rational(a * c, b * d)
    }
    operator fun minus(rational: Rational): Rational {
        val a = this.n * rational.d
        val b = this.d * rational.d
        val c = rational.n * this.d
        return Rational(a - c, b)
    }
    operator fun div(rational: Rational): Rational {
        val a = this.n
        val b = this.d
        val c = rational.n
        val d = rational.d
        return Rational(a * d, b * c)
    }
    operator fun unaryMinus(): Rational {
        val a = this.n
        val b = this.d
        return Rational(-a, b)
    }
    override fun compareTo(other: Rational): Int {
        return this.n.times(other.d).compareTo(other.n.times(this.d))
    }
    override fun equals(other: Any?): Boolean {
        return if (other is Rational) {
            this.n.times(other.d).equals(other.n.times(this.d))
        } else false
    }
    override fun toString(): String {
        val g = gcd(n, d)
        var a = n.div(g)
        var b = d.div(g)
        if (b < BigInteger.valueOf(0L)) {
            a = -a
            b = -b
        }
        if (b.equals(1L.toBigInteger())) return a.toString()
        return "$a/$b"
    }

    fun gcd(a: BigInteger, b: BigInteger): BigInteger {
        return if (b.equals(BigInteger.valueOf(0L))) a else gcd(b, a % b)
    }

}

infix fun Number.divBy(i: Number): Rational {
    val t = this.toLong().toBigInteger()
    val o = i.toLong().toBigInteger()
    return Rational(t, o)
}

fun String.toRational(): Rational {
    val thisString = this.split("/")
    var b = 1L.toBigInteger()
    if (thisString.size > 1) b = thisString[1].toBigInteger()
    return Rational(thisString[0].toBigInteger(), b)
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}




