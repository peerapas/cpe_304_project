package Assembler

import java.io.File
import kotlin.math.*


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

fun convertRtype(inputInstruction: instructionModel):MutableList<Int>{
    val outputBinary :MutableList<Int> = mutableListOf<Int>(0,0,0,0,0,0,0)
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
    val outputBinary :MutableList<Int> = mutableListOf<Int>(0,0,0,0,0,0,0)
    if(inputInstruction.field2.toIntOrNull() == null)
    {
        for(instruction in instructionCollection)
        {
            if(inputInstruction.field2 == instruction.label)
            {
                outputBinary.addAll(opcodeMap.getValue(inputInstruction.instruction))
                outputBinary.addAll((toBinary(inputInstruction.field0.toInt())))
                outputBinary.addAll(toBinary(inputInstruction.field1.toInt()))
                outputBinary.addAll(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
                outputBinary.addAll(toBinary(instructionCollection.indexOf(instruction)))
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

fun convertJtype(inputInstruction: instructionModel):MutableList<Int>{
    val outputBinary :MutableList<Int> = mutableListOf<Int>(0,0,0,0,0,0,0)
    outputBinary.addAll(opcodeMap.getValue(inputInstruction.instruction))
    outputBinary.addAll((toBinary(inputInstruction.field0.toInt())))
    outputBinary.addAll(toBinary(inputInstruction.field1.toInt()))
    outputBinary.addAll(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    return outputBinary
}

fun convertOtype(inputInstruction: instructionModel):MutableList<Int>{
    val outputBinary :MutableList<Int> = mutableListOf<Int>(0,0,0,0,0,0,0)
    outputBinary.addAll(opcodeMap.getValue(inputInstruction.instruction))
    outputBinary.addAll(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    return outputBinary
}

fun convertFillType(inputInstruction: instructionModel, instructionCollection: MutableList<instructionModel>):Int?{
    var output:Int? = null
    if(inputInstruction.field0.toIntOrNull() == null)
    {
        for(instruction in instructionCollection){
            if(inputInstruction.field0 == instruction.label)
            {
                output = instructionCollection.indexOf(instruction)
            }
        }
    }
    else
    {
        output = inputInstruction.field0.toInt()
    }
    return output
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

fun toDecimal(inputBinary: MutableList<Int>) :Int{
    var outputDec :Double = 0.0
    inputBinary.reverse()
    inputBinary.forEachIndexed { index, i ->
        if(i == 1)
        {
            outputDec += 2.toDouble().pow(index)
        }
    }
    return outputDec.toInt()
}

fun toHexa(inputDec: Int ) : MutableList<String>{
    var dec = inputDec
    val hex = mutableListOf<String>()
    var mod = 0
    do{
        mod = dec % 16
        when(mod){
            10 -> hex.add("A")
            11 -> hex.add("B")
            12 -> hex.add("C")
            13 -> hex.add("D")
            14 -> hex.add("E")
            15 -> hex.add("F")
            else -> {
                hex.add(mod.toString())
            }
        }
        dec /= 16
    }while (dec > 0)
    hex.reverse()
    return hex
}
fun main(){
    val filename = "src/simulator.txt"
    File(filename).createNewFile()
    val simulator = File(filename)
    val instructionCollection:MutableList<instructionModel> = mutableListOf()
    File("src/Assembler/assembly.txt").forEachLine {
        var instructionLine = it.split(" " )
        var instructionModel = instructionModel(instructionLine)
        instructionCollection.add(instructionModel)
    }
    instructionCollection.forEachIndexed { index, instruction ->

        print("(address $index): ")
        if(instruction.type == "R")
        {
            print(toDecimal(convertRtype(instruction)))
            print(" (hex 0x")
            toHexa(toDecimal(convertRtype(instruction))).forEach { print(it) }
            println(")")
        }
        else if(instruction.type == "I")
        {
            print(toDecimal(convertItype(instruction, instructionCollection)))
            print(" (hex 0x")
            toHexa(toDecimal(convertItype(instruction, instructionCollection))).forEach { print(it) }
            println(")")
        }
        else if(instruction.type == "J")
        {
            print(toDecimal(convertJtype(instruction)))
            print(" (hex 0x")
            toHexa(toDecimal(convertJtype(instruction))).forEach { print(it) }
            println(")")
        }
        else if(instruction.type == "O")
        {
            print(toDecimal(convertOtype(instruction)))
            print(" (hex 0x")
            toHexa(toDecimal(convertOtype(instruction))).forEach { print(it) }
            println(")")
        }
        else if(instruction.type == ".fill")
        {
            print(convertFillType(instruction, instructionCollection))
            print(" (hex 0x")
            print(convertFillType(instruction, instructionCollection).toString().format("%02X"))
            println(")")
        }
    }
}