import java.lang.Integer.max
import java.lang.Integer.parseInt
import java.lang.NumberFormatException
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    if (args.size != 2) {
        println("두개의 인자를 입력하세요.")
        exitProcess(0)
    }
    println(sumIntString(args[0], args[1]))
}


fun sumIntString(first: String, second: String): String {
    val firstR = first.reversed()
    val secondR = second.reversed()
    val len = max(first.length,second.length)


    val pos = mutableMapOf<Int,Int>()

    for ( (i,num) in (0 until len).withIndex() ){

        val (sum , over) = sumInt(firstR.getOrNull(i),secondR.getOrNull(i),pos[i])
        pos[i] = sum
        pos[i+1] = over
    }

    var s = ""
    for( i in 0 until pos.keys.size){
        s = pos[i].toString() + "$s"
    }
    return s.trimStart{it == '0' }.let {
        if (it.isBlank()) {
            "0"
        }else{
            it
        }
    }
}

fun sumInt(first: Char?, second: Char?, over: Int?): Pair<Int, Int> {
    val sum = toIntOfZero(first) + toIntOfZero(second) + (over ?: 0)
    return if(sum < 10)
        sum to 0
    else
        sum-10 to 1
}

private fun toIntOfZero(num: Char?) : Int{
    return try {
        parseInt(num.toString()) ?: 0
    } catch (e: NumberFormatException) {
        0
    }
}
