package me.nuty.android.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnSub = findViewById<Button>(R.id.btn_sub)
        val btnMul = findViewById<Button>(R.id.btn_mul)
        val btnDiv = findViewById<Button>(R.id.btn_div)

        val firstNum = findViewById<EditText>(R.id.first_num)
        val secondNum = findViewById<EditText>(R.id.second_num)
        val resultNum = findViewById<TextView>(R.id.result_num)

        btnAdd.setOnClickListener {
            resultNum.text = calculate(firstNum, secondNum, Operation.ADD).toString()
        }
        btnSub.setOnClickListener {
            resultNum.text = calculate(firstNum, secondNum, Operation.SUBTRACT).toString()
        }
        btnMul.setOnClickListener {
            resultNum.text = calculate(firstNum, secondNum, Operation.MULTIPLY).toString()
        }
        btnDiv.setOnClickListener {
            resultNum.text = calculate(firstNum, secondNum, Operation.DIVIDE).toString()
        }
    }

    private fun calculate(a: EditText, b: EditText, operation: Operation): Any? {
        val first = a.text.toString().toIntOrNull() ?: return "올바른 숫자를 입력해주세요."
        val second = b.text.toString().toIntOrNull() ?: return "올바른 숫자를 입력해주세요."

        return operation.evaluate(first, second)
    }

    enum class Operation(val evaluate: (a: Int, b: Int) -> Any) {
        ADD({ a, b -> a + b }),
        SUBTRACT( {a, b -> a - b }),
        MULTIPLY({ a, b -> a * b }),
        DIVIDE({ a, b ->
            if (b == 0) "0으로 나누기"
            else a.toDouble() / b
        })
    }
}
