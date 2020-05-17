package me.nuty.android.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigInteger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculator = Calculator()

        val buttonOperationMap = HashMap<Button, Calculator.Operation>().apply {
            put(btn_add, Calculator.Operation.ADD)
            put(btn_sub, Calculator.Operation.SUBTRACT)
            put(btn_mul, Calculator.Operation.MULTIPLY)
            put(btn_div, Calculator.Operation.DIVIDE)
        }

        for (buttonOperation in buttonOperationMap) {
            buttonOperation.key.setOnClickListener {
                val isNumberFieldValid = first_num.text.toString().toBigIntegerOrNull() != null
                        && second_num.text.toString().toBigIntegerOrNull() != null

                if (isNumberFieldValid) {
                    result_num.text = Calculator().apply {
                        operand1 = first_num.text.toString().toBigInteger()
                        operand2 = second_num.text.toString().toBigInteger()
                        operator = buttonOperation.value
                    }.run().toString()

                    expression.text = buttonOperation.value.display.format(first_num.text, second_num.text)
                } else {
                    Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

class Calculator() {

    var operator = Operation.ADD
    var operand1: BigInteger = BigInteger.ZERO
    var operand2: BigInteger = BigInteger.ZERO

    fun run(): Any = operator.calculate(operand1, operand2)

    enum class Operation(val display: String, val calculate: (a: BigInteger, b: BigInteger) -> Any) {
        ADD("%s + %s =", { a, b -> a + b }),
        SUBTRACT("%s - %s =", { a, b -> a - b }),
        MULTIPLY("%s × %s =", { a, b -> a * b }),
        DIVIDE("%s / %s =", { a, b ->
            if (b.compareTo(java.math.BigInteger.ZERO) != 0) a.toBigDecimal().divide(b.toBigDecimal(), 15, java.math.RoundingMode.HALF_UP).stripTrailingZeros()
            else "DIV/0"
        })
    }
}
