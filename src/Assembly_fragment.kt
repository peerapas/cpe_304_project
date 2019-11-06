import java.io.File


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
//            println(instructionModel.instruction)
//            println(opcodeMap.getValue(instructionModel.instruction))
            binary.addAll(opcodeMap.getValue(instructionModel.instruction))
            println(binary)

        }
    }
}