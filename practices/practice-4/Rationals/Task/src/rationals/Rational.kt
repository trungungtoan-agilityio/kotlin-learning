package rationals

import java.math.BigInteger

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

class Rational(val n: BigInteger, val d: BigInteger) : Comparable<Rational> {

    init {

    }

    operator fun plus(r: Rational): Rational = (n.times(r.d).plus(r.n.times(d))).divBy(r.d.times(d))

    operator fun minus(r: Rational): Rational = (n.times(r.d).minus(r.n.times(d))).divBy(r.d.times(d))

    operator fun times(r: Rational): Rational = n.times(r.n).divBy(r.d.times(d))

    operator fun div(r: Rational): Rational = n.times(r.d).divBy(d.times(r.n))

    operator fun unaryMinus(): Rational = Rational(n.negate(), d)

    override fun compareTo(r: Rational): Int = n.times(r.d).compareTo(r.n.times(d))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Rational

        val thisN = simplify(this)
        val otherN = simplify(other)

        return thisN.n.toDouble().div(thisN.d.toDouble()) == (otherN.n.toDouble().div(otherN.d.toDouble()))
    }

    override fun toString(): String {
        return when {
            d == 1.toBigInteger() || n.rem(d) == 0.toBigInteger() -> n.div(d).toString()
            else -> {
                val r = simplify(this)

                if(r.d < 0.toBigInteger() || (r.n < 0.toBigInteger() && r.d < 0.toBigInteger())){
                    formatRational(Rational(r.n.negate(), r.d.negate()))
                } else {
                    formatRational(Rational(r.n, r.d))
                }
            }
        }
    }

}

fun formatRational(r: Rational) : String = r.n.toString() + "/" + r.d.toString()


infix fun Int.divBy(r2: Int) : Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun Long.divBy(r2: Long) : Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun BigInteger.divBy(r2: BigInteger) : Rational = Rational(this, r2)

fun String.toRational(): Rational {
    val number = split("/")

    return when {
        number.size == 1 -> Rational(number[0].toBigInteger(), 1.toBigInteger())
        else -> Rational(number[0].toBigInteger(), number[1].toBigInteger())
    }
}

fun hcf(n1: BigInteger, n2: BigInteger): BigInteger =
        if (n2 != 0.toBigInteger()) hcf(n2, n1 % n2) else n1

fun simplify(r1: Rational): Rational {
    val hcf = hcf(r1.n, r1.d).abs()
    return Rational(r1.n.div(hcf), r1.d.div(hcf))
}
