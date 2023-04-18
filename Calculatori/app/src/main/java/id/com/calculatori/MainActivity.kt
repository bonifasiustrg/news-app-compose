package id.com.calculatori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var kotakInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kotakInput = findViewById(R.id.kotakInput)

    }

    fun onDigit(view: View) {
//        Toast.makeText(this, "The button clicked", Toast.LENGTH_SHORT).show()
        kotakInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        kotakInput?.text = ""
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(kotakInput?.text.toString())){
            kotakInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }

    }
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            kotakInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var inputValue = kotakInput?.text.toString()
            var prefix = ""
            try {
                if (inputValue.startsWith("-")){
                    prefix = "-"
                    inputValue = inputValue.substring(1)
                }
                if (inputValue.contains("-")){
                    var splitInput = inputValue.split("-")

                    var satu = splitInput[0]
                    var dua = splitInput[1]

                    if (prefix.isNotEmpty()){
                        satu = prefix + satu
                    }
                    kotakInput?.text = removeZeroAfterDot((satu.toDouble() - dua.toDouble()).toString())
                }else if (inputValue.contains("+")){
                    var splitInput = inputValue.split("+")

                    var satu = splitInput[0]
                    var dua = splitInput[1]

                    if (prefix.isNotEmpty()){
                        satu = prefix + satu
                    }
                    kotakInput?.text = removeZeroAfterDot((satu.toDouble() + dua.toDouble()).toString())
                }else if (inputValue.contains("*")){
                    var splitInput = inputValue.split("*")

                    var satu = splitInput[0]
                    var dua = splitInput[1]

                    if (prefix.isNotEmpty()){
                        satu = prefix + satu
                    }
                    kotakInput?.text = removeZeroAfterDot((satu.toDouble() * dua.toDouble()).toString())
                }else if (inputValue.contains("/")){
                    var splitInput = inputValue.split("/")

                    var satu = splitInput[0]
                    var dua = splitInput[1]

                    if (prefix.isNotEmpty()){
                        satu = prefix + satu
                    }
                    kotakInput?.text = removeZeroAfterDot((satu.toDouble() / dua.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
//                println(e.message)
            }
        }
    }

    private fun isOperatorAdded(value:String) : Boolean {
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
    private fun removeZeroAfterDot(result : String) : String{
        var finalValue = result
        var n = result.length
        if (result.contains(".0") && result.substring(n-2)==".0"){
            finalValue = result.substring(0, result.length-2)
        }
        return finalValue
    }



//END
}


