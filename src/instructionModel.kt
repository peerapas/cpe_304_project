//class instructionModel(instruction:String?, field0:String?, field1:String?, field2:String?) {
class instructionModel {

    var label :String? = null
    var instruction :String? = null
    var field0 :String? = null
    var field1 :String? = null
    var field2 :String? = null

    constructor(instructionLine: List<String>){
        if(instructionLine.size == 5)
        {
//            check for r$i type
            label = instructionLine[0]
            instruction = instructionLine[1]
            field0 = instructionLine[2]
            field1 = instructionLine[3]
            field2 = instructionLine[4]
        }
//        check for j type
        else if(instructionLine.size == 4)
        {
            label = instructionLine[0]
            instruction = instructionLine[1]
            field0 = instructionLine[2]
            field1 = instructionLine[3]
        }
//        check for o type
        else if(instructionLine.size == 2)
        {
            label = instructionLine[0]
            instruction = instructionLine[1]
        }

    }

}