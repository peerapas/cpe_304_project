//class instructionModel(instruction:String?, field0:String?, field1:String?, field2:String?) {
class instructionModel {

    var label :String = ""
    var instruction :String = ""
    var field0 :String = ""
    var field1 :String = ""
    var field2 :String = ""
    var type :String = ""

    constructor(instructionLine: List<String>){
        if(instructionLine.size == 5)
        {
//            check for r$i type
            label = instructionLine[0]
            instruction = instructionLine[1]
            field0 = instructionLine[2]
            field1 = instructionLine[3]
            field2 = instructionLine[4]
            if(instructionLine.contains("add") || instructionLine.contains("nand"))
            {
                type = "R"
            }
            else
            {
                type = "I"
            }
        }
//        check for j type
        else if(instructionLine.size == 4)
        {
            label = instructionLine[0]
            instruction = instructionLine[1]
            field0 = instructionLine[2]
            field1 = instructionLine[3]
            type = "J"
        }
//        check for o type
        else if(instructionLine.size == 2)
        {
            label = instructionLine[0]
            instruction = instructionLine[1]
            type = "O"
        }
        else if(instructionLine.contains(".fill"))
        {
            label = instructionLine[0]
            instruction = instructionLine[1]
            field0 = instructionLine[2]
            type = ".fill"
        }
    }

}