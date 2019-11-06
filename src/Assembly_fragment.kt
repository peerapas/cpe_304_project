import java.io.File


fun main() {
    File("src/assembly.txt").forEachLine {
        var instructionLine = it.split(" ")
        var instructionModel = instructionModel(instructionLine)
        println(instructionModel.instruction)
        val opcodeMap = mapOf<String, String>("add" to "000", "nand" to "001", "lw" to "010", "sw" to "011","beq" to "100", "jalr" to "101", "halt" to "110", "noop" to "111")

    }
}