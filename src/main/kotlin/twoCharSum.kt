import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("두개의 인자를 입력하세요.")
        exitProcess(0)
    }
    println(twoCharSum(args[0], args[1]))
}


fun twoCharSum(first:String, second:String): String {
    return sumIntString(chartoNumstring(first),chartoNumstring(second))
}


private val YUK = "억"

private val JO = "조"

private val YUG = "영"

fun chartoNumstring(str : String) : String{

    val map = mutableMapOf<String,String>().apply {
        put(JO,"")
        put(YUK,"")
        put(YUG,"")
    }

    var key = YUG
    for (c in str.reversed()) {
        if (map.keys.contains(c.toString())) {
            key = c.toString()
        }
        map[key] = map[key] + c
    }



    val jo = if (map[JO] == JO) {
        "1"
    }else if(map[JO]?.length == 0){
        "0"
    }else
        reverseBillionToNum(map[JO]?.substring(1))


    val yuknum = if (map[YUK] == YUK) {
        "1"
    }else if(map[YUK]?.length == 0){
        "0"
    }else {
        reverseThousandToNum(map[YUK]?.substring(1))
    }.padStart(4,'0')

    val younh = if (map[YUG] == YUG) {
        "1"
    }else
        reverseBillionToNum(map[YUG])

    return (jo+yuknum+younh).trimStart('0')
}

private val MAN = "만"

fun reverseBillionToNum(str: String?) : String{

    val str = str ?: return ""
    val map = mutableMapOf<String,String>().apply {
        put(MAN,"")
        put(YUG,"")
    }


    var key = YUG
    for (c in str) {
        if (map.keys.contains(c.toString())) {
            key = c.toString()
        }
        map[key] = map[key] + c
    }
    val man = map[MAN]?.let {
        if (it == MAN) {
            "1"
        }else if(it.length == 0){
          "0"
        } else{
            reverseThousandToNum(it.substring(1))
        }
    } ?: "0"

    val yung = map[YUG]?.let {
        reverseThousandToNum(it)
    } ?: "0"

    return man.padStart(4,'0') + yung.padStart(4,'0')
}

//  천백십일 // 이천삼백이십일
fun reverseThousandToNum(str: String?): String {
    val str = str ?: return ""
    val map = mutableMapOf<String,String>().apply {
        put("천","")
        put("백","")
        put("십","")
        put(YUG,"")
    }
    var key = YUG
    for (c in str) {
        if (map.keys.contains(c.toString())) {
            key = c.toString()
        }
        map[key] = c.toString() +  map[key]
    }

    var num = 0
    num += charToDigits(map[YUG],1, YUG)
    num += charToDigits(map["십"],10,"십")
    num += charToDigits(map["백"],100,"백")
    num += charToDigits(map["천"],1000,"천")

    return num.toString()
}

private fun charToDigits(chars: String?, mux: Int, checker: String): Int {

    val chars = chars?.getOrNull(0) ?: return 0
    return when (chars.toString()) {
        checker -> 1
        "일" -> 1
        "이" -> 2
        "삼" -> 3
        "사" -> 4
        "오" -> 5
        "육" -> 6
        "칠" -> 7
        "팔" -> 8
        "구" -> 9
        else -> 0
    } * mux
}


// 일십백천 순서임.
fun reverseThousandNumberToString(str:String) : String {

    val list = str.split("").toMutableList().filter { it.isNotBlank() }

    val clist = list.map {
        it.toCharString()
    }.mapIndexed{ i, v ->
        when{
            v == YUG -> ""
            i == 0 -> v //  "아무것도 하지 않음"
            i == 1 -> {  // 십의자리.
                appendIfOne(v,"십")
            }
            i == 2 ->{
                appendIfOne(v,"백")
            }
            i == 3 ->{
                appendIfOne(v,"천")
            }
            else -> YUG
        }
    }.filter { it != YUG }

    return clist.reversed().joinToString("")
}

private fun appendIfOne(v: String,app:String): String {
    return if (v == "일") {
        app
    } else {
        v + app
    }
}

private fun String.toCharString(): String {
    return when(this){
        "0" -> YUG
        "1" -> "일"
        "2" -> "이"
        "3" -> "삼"
        "4" -> "사"
        "5" -> "오"
        "6" -> "육"
        "7" -> "칠"
        "8" -> "팔"
        "9" -> "구"
        else -> YUG
    }
}

fun numberToString(str:String) : String{
    val rstr = str.reversed()

    val one = rstr.substringSafe(0..3)
    val man = rstr.substringSafe(4..7)
    val yuk = rstr.substringSafe(8..11)
    val jo = rstr.substringSafe(12..15)
    val manJo = rstr.substringSafe(16..str.length)


    val onestr = reverseThousandNumberToString(one)
    var manstr = reverseThousandNumberToString(man)
    val yukstr = reverseThousandNumberToString(yuk)
    val jostr = reverseThousandNumberToString(jo)
    var manjostr = reverseThousandNumberToString(manJo)

    if (manstr.isNotBlank()) {
        manstr +="만"
    }
    val oneString = manstr+onestr

    var yukString = ""
    if (yukstr.isNotBlank()) {
        yukString = yukstr + YUK
    }

    if (manjostr.isNotBlank()) {
        manjostr +="만"
    }
    var joString = manjostr+jostr
    if (joString.isNotBlank()) {
        joString += JO
    }

    return (joString+yukString+oneString).let {
        if (it.startsWith("일만")) {
            it.substring(1)
        }else{
            it
        }
    }
}

private fun String.substringSafe(r: IntRange): String {
    if(r.first >= length) return ""
    return try{
        this.substring(r)
    }catch (e : StringIndexOutOfBoundsException){
        this.substring(r.first until this.length)
    }
}
