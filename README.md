# calculator

_**Zahhard**_

<img src="https://user-images.githubusercontent.com/91548634/155278976-da7234a2-5200-47b3-bf39-580360e38e21.jpeg" width="200" height="400" />

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var caUseOperator = false
    private var caUseDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        buttonClicked()
    }

    //**********************************************************************************************

    private fun buttonClicked() {

        // Numbers
        binding.one.setOnClickListener(::numberClicked)
        binding.tow.setOnClickListener (::numberClicked)
        binding.three.setOnClickListener (::numberClicked)
        binding.four.setOnClickListener (::numberClicked)
        binding.five.setOnClickListener (::numberClicked)
        binding.six.setOnClickListener (::numberClicked)
        binding.seven.setOnClickListener (::numberClicked)
        binding.eight.setOnClickListener (::numberClicked)
        binding.nine.setOnClickListener (::numberClicked)
        binding.zero.setOnClickListener (::numberClicked)
        binding.dot.setOnClickListener (::numberClicked)

        // Operators
        binding.division.setOnClickListener (::operatorClicked)
        binding.x.setOnClickListener (::operatorClicked)
        binding.plus.setOnClickListener (::operatorClicked)
        binding.minus.setOnClickListener (::operatorClicked)
        binding.btnRadical.setOnClickListener(::del)
        binding.equals.setOnClickListener(::equal)
    }
    //**********************************************************************************************

    private fun numberClicked(view: View){

        if (view is Button){

            if (view.text == "."){
                if ( caUseDot ){
                    binding.textOne.append(view.text)
                    Toast.makeText(this, "${view.text}", Toast.LENGTH_SHORT).show()
                    caUseDot = false
                }
            }
            else{
                binding.textOne.append(view.text)
                Toast.makeText(this, "${view.text}", Toast.LENGTH_SHORT).show()
                caUseDot = true
            }
            caUseOperator = true
        }
    }

    //**********************************************************************************************

    private fun operatorClicked(view: View){

        if (view is Button && caUseOperator){
            binding.textOne.append(view.text)
            Toast.makeText(this, "${view.text}", Toast.LENGTH_SHORT).show()
            caUseOperator = false
        }
    }

    //**********************************************************************************************

    private fun del(view: View) {
        binding.textOne.text = ""
        binding.textTow.text = ""
    }

    //**********************************************************************************************

    private fun equal(view: View){
      // Toast.makeText(this, "${binding.textOne.text}", Toast.LENGTH_SHORT).show()
        binding.textTow.text = calcRes()
    }

    //**********************************************************************************************

    private fun calcRes(): String{
        val digits = calcDigits()
        if (digits.isEmpty())
            return ""

        val turn1 = turn(digits)
        if (turn1.isEmpty())
            return ""

        val result = addPlusAndMinuc(turn1)
        return result.toString()
    }

    //**********************************************************************************************

    private fun calcDigits(): MutableList<Any>{
        val listOfAllThings = mutableListOf<Any>()
        var num = ""
        var temp = ""
            for (char in textOne.text) {
                if (char.isDigit() || char == '.') {
                    num += char
                } else {
                    listOfAllThings.add(num.toFloat())
                    num = ""
                    listOfAllThings.add(char)
                }
            }
            if (num != "") {
                listOfAllThings.add(num.toFloat())
            }
        return listOfAllThings
    }

    ///*********************************************************************************************

    private fun addPlusAndMinuc(list: MutableList<Any>): Float {
        var result = list[0] as Float
        for (o in list.indices){
            if (list[o] is Char && o != list.lastIndex){
                val operator = list[o]
                var second = list[o+1] as Float
                if (operator == '+'){
                    result += second
                }
                if (operator == '-'){
                   result -= second
                }
            }
        }
        return result
    }

    //**********************************************************************************************

    private fun turn(digits: MutableList<Any>): MutableList<Any>{
        var list = digits
        while (list.contains('x') || list.contains('/')){
            list = calcTurn(list)
        }
        return list
    }

    //**********************************************************************************************

    private fun calcTurn(list: MutableList<Any>): MutableList<Any>{
        val newList = mutableListOf<Any>()
        var restartIndex = list.size

        for (i in list.indices){
            if (list[i] is Char && i != list.lastIndex && i < restartIndex) {
                val operator = list[i]
                val first = list[i - 1] as Float
                val second = list[i + 1] as Float

                when (operator) {
                    'x' -> {
                        newList.add(first * second)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(calculator.division(first, second))
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(first)
                        newList.add(operator)
                    }
                }
            }
            if (i > restartIndex)
                newList.add(list[i])
        }
    return newList
    }



}
