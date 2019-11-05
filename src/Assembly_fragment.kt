import java.io.File

fun convertCode(){

}


fun loadInstructionModel(instructionLine:List<String>):instructionModel{
    var instruction :String? = null
    var field0 :String? = null
    var field1 :String? = null
    var field2 :String? = null
    if(instructionLine.size == 4)
    {
//            check for r$i type
        instruction = instructionLine[0]
        field0 = instructionLine[1]
        field1 = instructionLine[2]
        field2 = instructionLine[3]
    }
//        check for j type
    else if(instructionLine.size == 3)
    {
        instruction = instructionLine[0]
        field0 = instructionLine[1]
        field1 = instructionLine[2]
    }
//        check for o type
    else if(instructionLine.size == 1)
    {
        instruction = instructionLine[0]
    }
    return instructionModel(instruction, field0, field1, field2)
}



fun main() {
    File("src/assembly.txt").forEachLine {
        var instructionLine = it.split(" ")
        var instructionModel = loadInstructionModel(instructionLine)
    }
}