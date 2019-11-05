import java.io.File

fun convertCode(){

}
fun main() {
    File("src/assembly.txt").forEachLine {
        var instructionLine = it.split(" ")
        if(instructionLine.size == 4)
        {
//            check for r$i type
            var instruction = instructionLine[0]
            var field0 = instructionLine[1]
            var field1 = instructionLine[2]
            var field2 = instructionLine[3]
            println(instruction + field0 + field1 + field2)
        }
//        check for j type
        else if(instructionLine.size == 3)
        {
            var instruction = instructionLine[0]
            var field0 = instructionLine[1]
            var field2 = instructionLine[2]
        }
//        check for o type
        else if(instructionLine.size == 1)
        {
            var instruction = instructionLine[0]
        }
    }
}