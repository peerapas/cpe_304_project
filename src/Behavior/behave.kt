import java.io.File

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
        = File(fileName).useLines { it.toList() }

fun nand(num1: MutableList<Int>,num2: MutableList<Int>): MutableList<Int> {
    var temp=0
    val listOfres = mutableListOf<Int>()
    var x = 0
    var max1=num1.size-1
    var max2=num2.size-1
    if (num1.size>=num2.size){
        for (i in num1){
            if (max2-x<0){
                temp = num1[max1-x] and 0
            }
            else {
                temp = num1[max1-x] and num2[max2-x]
            }
            if(temp == 1) listOfres.add(0)
            else if(temp == 0) listOfres.add(1)
            x+=1

        }
    }
    else if(num2.size > num1.size){
        for (i in num2){
            if (max1-x<0){
                temp = num2[max2-x] and 0
            }
            else {
                temp = num1[max1-x] and num2[max2-x]
            }
            println("temp: $temp")
            if(temp == 1) listOfres.add(0)
            else if(temp == 0) listOfres.add(1)
            x+=1
        }
    }
    return listOfres
}

fun convertBinaryToDecimal(num: Long): Int {
    var num = num
    var decimalNumber = 0
    var i = 0
    var remainder: Long
    while (num.toInt() != 0) {
        remainder = (num % 10).toLong()
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

fun convert2complement(num: Long): Int{
    var num2 = toBinary(num.toInt())
    var num3 = 0
    if (num2[15]==1){
        var str = num2[1].toString()+num2[0].toString()
        var num3 = convertBinaryToDecimal(str.toLong())-1
    }
    return num3.inv()
}

fun main() {

    var mem = mutableListOf<Long>()
    var reg = mutableListOf<Long>(0, 0, 0, 0, 0, 0, 0, 0)
    var pc = 0
    var fileS = "src/simulator.txt"
    val file2 = "nand.txt"
    var fileOb = File(file2)
    fileOb.createNewFile()
    var inst = 0


    //var fileObject = File(fileS)
    var text = readFileAsLinesUsingUseLines(fileS)

    var i = 2

    for (x in text) {
        var data = x.split(" ")
        mem.add(data[i].toLong())
    }
    var findOp = mutableListOf<Int>()
   // var currentline = mutableListOf<Long>()
    //var Run = true
    println("@@@")
    println("state:")
    println("pc: $pc")
    println("memory:")
    println(mem)
    var p = 0
    println("register:")
    for (k in reg) {
        println("   reg[$p ]: " + reg[p])
        p += 1
    }
    pc+=1
    var Run = true
    while (Run) {
        //currentline.add(mem[pc])
        //println(mem[pc])
        //println(currentline)
        //var pl = pc+1
        println("@@@")
        println("state:")
        println("pc: $pc")
        println("memory:")
        println(mem)
        findOp = toBinary(mem[pc-1].toInt())
        //findOp = toBinary(currentline[pc].toInt())
//        println(currentline[pc])
//        println(currentline[pc].toInt())
//        println(pc)
//        println(findOp)
        println("current addr: " +mem[pc])
        if (findOp.size > 3) {
            while (findOp.size < 32) {
                findOp.add(0, 0)
            }
            //println(findOp)
            var op = findOp[7].toString() + findOp[8].toString() + findOp[9].toString()
            var rs =
                convertBinaryToDecimal((findOp[10].toString() + findOp[11].toString() + findOp[12].toString()).toLong())
            var rt =
                convertBinaryToDecimal((findOp[13].toString() + findOp[14].toString() + findOp[15].toString()).toLong())
            println("rs: $rs rt: $rt op: $op")
            if (op == "000") { //R type
                var des =
                    convertBinaryToDecimal((findOp[29].toString() + findOp[30].toString() + findOp[31].toString()).toLong())
                reg[des] = reg[rs] + reg[rt]
                //println(reg[des])
                pc += 1
            }
            //        else if (op == "001") { //R type
            //            var des = convertBinaryToDecimal((findOp[29].toString()+findOp[30].toString()+findOp[31].toString()).toLong())
            //
            //            //println("rs1: $rs1 rs2: $rs2 des: $des")
            //            var temp = mutableListOf<Int>()
            //            //println("regrs1: " + toBinary(reg[rs])+  " regrs2: "+ toBinary(reg[rs2]) )
            //            temp = nand(toBinary(reg[rs]), toBinary(reg[rt]))
            //            fileOb.writeText(temp.toString())
            //            var result = fileOb.readText()
            //            //println("binary result: $result and pc: $pc")
            //            pc += 1
            //        }
            else if (op == "010") { //I type -load
                var off = convertBinaryToDecimal(
                    (findOp[16].toString() + findOp[17].toString() + findOp[18].toString() + findOp[19].toString() +
                            findOp[20].toString() + findOp[21].toString() + findOp[22].toString() + findOp[23].toString() +
                            findOp[24].toString() + findOp[25].toString() + findOp[26].toString() + findOp[27].toString() +
                            findOp[28].toString() + findOp[29].toString() + findOp[30].toString() + findOp[31].toString()).toLong()
                )
                println("rs: $rs rt: $rt off: $off")
                reg[rt] = mem[reg[rs].toInt() + off]
//                println(rt)
//                println(reg[rt])
//                println(reg[rs])
                pc += 1

            }
            else if (op == "011"){ //I type -store
                var off = convertBinaryToDecimal((findOp[16].toString()+findOp[17].toString()+findOp[18].toString()+findOp[19].toString()+
                        findOp[20].toString()+findOp[21].toString()+ findOp[22].toString()+findOp[23].toString()+
                        findOp[24].toString()+findOp[25].toString()+findOp[26].toString()+findOp[27].toString()+
                        findOp[28].toString()+findOp[29].toString()+findOp[30].toString()+findOp[31].toString()).toLong())
                //println("rs1: $rs1 rs2: $rs2 off: $off")
                mem[reg[rs].toInt() + off]=reg[rt]
                pc+=1
            }
            else if (op == "100"){ //I type
                var off = convertBinaryToDecimal((findOp[16].toString()+findOp[17].toString()+findOp[18].toString()+findOp[19].toString()+
                        findOp[20].toString()+findOp[21].toString()+ findOp[22].toString()+findOp[23].toString()+
                        findOp[24].toString()+findOp[25].toString()+findOp[26].toString()+findOp[27].toString()+
                        findOp[28].toString()+findOp[29].toString()+findOp[30].toString()+findOp[31].toString()).toLong())
                println("rs: $rs rt: $rt off: $off")
//                println(reg[rs])
//                println(reg[rt])
//                println(off)
//                println (pc)
                if(reg[rs]==reg[rt]){
                    if (findOp[16]==1){
                        //println(convert2complement(off.toLong()))
                        pc+=convert2complement(off.toLong())-1
                    }
                    else{
                        pc+=1+off
                    }

                    //println(pc)
                    //pc=2
                }else{
                    pc+=1
                }
                //println(pc)
            }
            else if (op == "101"){ //J type
                reg[rt] = (pc+1).toLong()
                if (rs==rt){
                    pc += 1
                }else{
                    pc += reg[rs].toInt()
                }
            }
            else if(op == "110") { //O type
                pc += 1
                println("halted")
                println("Instruction = $inst" )
                Run = false
            }
            else if (op == "111"){} //O type -does nothings.
        }
        var v = 0
        println("register:")
        for (k in reg) {
            println("   reg[$v ]: " + reg[v])
            v += 1
        }
        println("end state")
        inst +=1

    }

}
