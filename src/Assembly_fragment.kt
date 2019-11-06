import java.io.File

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

//        binary.add(1)
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


fun main() {
    File("src/assembly.txt").forEachLine {
        var binary = mutableListOf<Int>(0,0,0,0,0,0,0)
        var hex = mutableListOf<Int>(0)
        var instructionLine = it.split(" " )
        var instructionModel = instructionModel(instructionLine)
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
//      push opcode to line of binary
        if(opcodeMap.containsKey(instructionModel.instruction)){
            binary.addAll(opcodeMap.getValue(instructionModel.instruction))
            binary.addAll(toBinary(instructionModel.field0.toInt()))
            println(binary)

        }

    }
}