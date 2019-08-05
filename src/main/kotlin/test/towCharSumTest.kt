package test

import chartoNumstring
import numberToString
import reverseThousandNumberToString
import twoCharSum

fun main(args: Array<String>) {
    charToNumStringTest()
//    numberToStringTest()
//    reverseThousandNumberToStringTest()
}

fun charToNumStringTest(inStr : String ,exp : String){
    val r = chartoNumstring(inStr)
    if(r == exp)
        println("PASS  $r == $exp")
    else
        error("NOT PASSSSSSSSSSSSSSSSSSSS  $r == $exp")
}


fun charToNumStringTest(){
    @Suppress("DuplicatedCode") val list = arrayListOf(
        "1" to "일",
        "11" to "십일",
        "111" to "백십일",
        "1111" to "천백십일",
        "11111" to "만천백십일",
        "111111" to "십일만천백십일",
        "1111111" to "백십일만천백십일",
        "11111111" to "천백십일만천백십일",
        "111111111" to "일억천백십일만천백십일",
        "1111111111" to "십일억천백십일만천백십일",
        "11111111111" to "백십일억천백십일만천백십일",
        "111111111111" to "천백십일억천백십일만천백십일",
        "1111111111111" to "일조천백십일억천백십일만천백십일",
        "11111111111111" to "십일조천백십일억천백십일만천백십일",
        "11111111111111111" to "만천백십일조천백십일억천백십일만천백십일",
        "12000013022000001300" to "천이백만십삼조이백이십억천삼백"

    ).map {
        it.second to it.first
    }.reversed()

    for ((i,e) in list) {
        charToNumStringTest(i,e)
    }
}

fun numberToStringTest(){
    val list = arrayListOf(
        "1" to "일",
        "11" to "십일",
        "111" to "백십일",
        "1111" to "천백십일",
        "11111" to "만천백십일",
        "111111" to "십일만천백십일",
        "1111111" to "백십일만천백십일",
        "11111111" to "천백십일만천백십일",
        "111111111" to "일억천백십일만천백십일",
        "1111111111" to "십일억천백십일만천백십일",
        "11111111111" to "백십일억천백십일만천백십일",
        "111111111111" to "천백십일억천백십일만천백십일",
        "1111111111111" to "일조천백십일억천백십일만천백십일",
        "11111111111111" to "십일조천백십일억천백십일만천백십일",
        "11111111111111111" to "만천백십일조천백십일억천백십일만천백십일"

    )

    for ((i,e) in list) {
        numberToStringTest(i,e)
    }

}

fun numberToStringTest(inStr : String ,exp : String){
    val r = numberToString(inStr)
    if(r == exp)
        println("PASS  $r == $exp")
    else
        println("NOT PASSSSSSSSSSSSSSSSSSSS  $r == $exp")

}

fun reverseThousandNumberToStringTest(){
    println(reverseThousandNumberToString("1111".reversed()))
    println(reverseThousandNumberToString("111".reversed()))
    println(reverseThousandNumberToString("11".reversed()))
    println(reverseThousandNumberToString("1".reversed()))
    println(reverseThousandNumberToString("0".reversed()))
    println(reverseThousandNumberToString("1234".reversed()))
    println(reverseThousandNumberToString("1000".reversed()))
    println(reverseThousandNumberToString("100".reversed()))
    println(reverseThousandNumberToString("10".reversed()))
    println(reverseThousandNumberToString("1".reversed()))
    println(reverseThousandNumberToString("1034".reversed()))
    println(reverseThousandNumberToString("1004".reversed()))
    println(reverseThousandNumberToString("1".reversed()))
    println(reverseThousandNumberToString("9999".reversed()))
    println(reverseThousandNumberToString("2000".reversed()))
    println(reverseThousandNumberToString("300".reversed()))
}