package me.nuty.android.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {
            result_num.text = calculate(first_num, second_num, Operation.ADD).toString()
        }
        btn_sub.setOnClickListener {
            result_num.text = calculate(first_num, second_num, Operation.SUBTRACT).toString()
        }
        btn_mul.setOnClickListener {
            result_num.text = calculate(first_num, second_num, Operation.MULTIPLY).toString()
        }
        btn_mul.setOnClickListener {
            result_num.text = calculate(first_num, second_num, Operation.DIVIDE).toString()
        }
    }

    private fun calculate(a: EditText, b: EditText, operation: Operation): Any? {
        val first = a.text.toString().toIntOrNull() ?: return "올바른 숫자를 입력해주세요."
        val second = b.text.toString().toIntOrNull() ?: return "올바른 숫자를 입력해주세요."

        return operation.evaluate(first, second)
    }

    enum class Operation(val evaluate: (a: Int, b: Int) -> Any) {
        ADD({ a, b -> a + b }),
        SUBTRACT({ a, b -> a - b }),
        MULTIPLY({ a, b -> a * b }),
        DIVIDE({ a, b ->
            if (b == 0) "0으로 나누기"
            else a.toDouble() / b
        })
    }
}
