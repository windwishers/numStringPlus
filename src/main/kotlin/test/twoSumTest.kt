package test

import sumIntString

fun main() {
    
    val r = sumIntString("1","2")
    runTest(1,2)
    runTest(1,3)
    runTest(1,1000000000)
    runTest(2000000000,2000000000,"4000000000")
}

fun runTest(num1: Int, num2: Int, sum :String = (num1 + num2).toString()): Boolean {
    val r = sumIntString(num1.toString(),num2.toString())
    if(r == sum){
        return true;
    }else{
        println(" $num1 + $num2 => expect : $sum actual : $r")
        return false
    }
}