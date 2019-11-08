import java.io.File
import kotlin.test.fail

val opcodeMap = mapOf<String?,List<Int>>(
    "add" to listOf<Int>(0,0,0),
    "nand" to listOf<Int>(0,0,1),
    "lw" to listOf<Int>(0,1,0),
    "sw" to listOf<Int>(0,1,1),
    "beq" to listOf<Int>(1,0,0),
    "jalr" to listOf<Int>(1,0,1),
    "halt" to listOf<Int>(1,1,0),
    "noop" to listOf<Int>(1,1,1)
)

fun convertRtype(inputInstruction:instructionModel):MutableList<Int>{
    val outputBinary :MutableList<Int> = mutableListOf()
    if(opcodeMap.containsKey(inputInstruction.instruction)){
        outputBinary.addAll(opcodeMap.getValue(inputInstruction.instruction))
        outputBinary.addAll(toBinary(inputInstruction.field0.toInt()))
        outputBinary.addAll(toBinary(inputInstruction.field1.toInt()))
        outputBinary.addAll(listOf(0,0,0,0,0,0,0,0,0,0,0,0,0))
        outputBinary.addAll(toBinary(inputInstruction.field2.toInt()))
    }
    return outputBinary
}

fun convertItype(inputInstruction: instructionModel, instructionCollection: MutableList<instructionModel>):MutableList<Int>{
    val outputBinary :MutableList<Int> = mutableListOf<Int>()
    if(inputInstruction.field2.toIntOrNull() == null)
    {
        for(instruction in instructionCollection)
        {
            if(inputInstruction.field2 == instruction.label)
            {

            }
        }
    }
    else
    {
        if(opcodeMap.containsKey(inputInstruction.instruction)) {
            outputBinary.addAll(opcodeMap.getValue(inputInstruction.instruction))
            outputBinary.addAll(toBinary(inputInstruction.field0.toInt()))
            outputBinary.addAll(toBinary(inputInstruction.field1.toInt()))
            outputBinary.addAll(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
            outputBinary.addAll(toBinary(inputInstruction.field2.toInt()))
        }
    }
    return outputBinary
}

fun convertJtype(){

}

fun convertOtype(){

}

fun convertFillType(){

}

fun toBinary(input: Int):MutableList<Int> {
    var dec :Int = input
    var binary:MutableList<Int> = mutableListOf()
    if(input == 0){
        binary.addAll(listOf(0,0,0))
        return binary
    }
    else if(input == 1){
        binary.addAll(listOf(0,0,1))
        return binary
    }
    else{
        while (dec >= 1){
            binary.add(dec%2)
            dec = dec/2
        }

        if(binary.size == 2){
            binary.add(0)
            binary.reverse()
            return binary
        }
        else{
            binary.reverse()
            return binary
        }
    }
}


fun main(){
    val instructionCollection:MutableList<instructionModel> = mutableListOf()
    File("src/assembly.txt").forEachLine {
        var binary = mutableListOf<Int>(0,0,0,0,0,0,0)
        var hex = mutableListOf<Int>(0)
        var instructionLine = it.split(" " )
        var instructionModel = instructionModel(instructionLine)
        instructionCollection.add(instructionModel)
//        println(instructionModel.instruction)
//      push opcode to line of binary
    }
    for(instruction in instructionCollection){
        if(instruction.type == "R")
        {
            println(convertRtype(instruction))
        }
    }

//    for(i in instructionCollection){
//        if(i.field2.toIntOrNull() == null && i.field2 != "") {
////            println(i.field2)
//            for(j in instructionCollection){
//                if(i.field2 == j.label)
//                {
//                    println(instructionCollection.indexOf(j))
//                }
//            }
//        }
//    }
}