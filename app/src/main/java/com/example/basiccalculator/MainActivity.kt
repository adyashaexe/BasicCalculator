package com.example.basiccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView

    private var currentInput = StringBuilder()
    private var firstOperand = 0.0
    private var pendingOperator = ""
    private var isNewOperation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Number buttons
        val numberIds = mapOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )
        for ((id, digit) in numberIds) {
            findViewById<Button>(id).setOnClickListener { onDigit(digit) }
        }

        // Decimal point
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDot() }

        // Operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { onOperator("−") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperator("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperator("÷") }

        // Equals
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEquals() }

        // Clear
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClear() }

        // Toggle sign
        findViewById<Button>(R.id.btnToggleSign).setOnClickListener { onToggleSign() }

        // Percent
        findViewById<Button>(R.id.btnPercent).setOnClickListener { onPercent() }
    }

    private fun onDigit(digit: String) {
        if (isNewOperation) {
            currentInput.clear()
            isNewOperation = false
        }
        // Prevent multiple leading zeros
        if (currentInput.toString() == "0" && digit == "0") return
        if (currentInput.toString() == "0" && digit != ".") currentInput.clear()

        currentInput.append(digit)
        tvDisplay.text = currentInput.toString()
    }

    private fun onDot() {
        if (isNewOperation) {
            currentInput.clear()
            currentInput.append("0")
            isNewOperation = false
        }
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) currentInput.append("0")
            currentInput.append(".")
            tvDisplay.text = currentInput.toString()
        }
    }

    private fun onOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            // Chain operations: calculate pending first
            if (pendingOperator.isNotEmpty() && !isNewOperation) {
                onEquals()
            }
            firstOperand = currentInput.toString().toDoubleOrNull() ?: 0.0
        }
        pendingOperator = op
        isNewOperation = true
    }

    @SuppressLint("SetTextI18n")
    private fun onEquals() {
        if (pendingOperator.isEmpty() || currentInput.isEmpty()) return

        val secondOperand = currentInput.toString().toDoubleOrNull() ?: return

        val result = when (pendingOperator) {
            "+"  -> firstOperand + secondOperand
            "−"  -> firstOperand - secondOperand
            "×"  -> firstOperand * secondOperand
            "÷"  -> {
                if (secondOperand == 0.0) {
                    tvDisplay.text = "Error"
                    resetState()
                    return
                }
                firstOperand / secondOperand
            }
            else -> return
        }

        val formatted = formatResult(result)
        tvDisplay.text = formatted
        currentInput.clear()
        currentInput.append(formatted)
        pendingOperator = ""
        isNewOperation = true
    }

    private fun onClear() {
        resetState()
        tvDisplay.text = "0"
    }

    private fun onToggleSign() {
        val value = currentInput.toString().toDoubleOrNull() ?: return
        val toggled = value * -1
        val formatted = formatResult(toggled)
        currentInput.clear()
        currentInput.append(formatted)
        tvDisplay.text = formatted
    }

    private fun onPercent() {
        val value = currentInput.toString().toDoubleOrNull() ?: return
        val result = value / 100
        val formatted = formatResult(result)
        currentInput.clear()
        currentInput.append(formatted)
        tvDisplay.text = formatted
    }

    private fun formatResult(value: Double): String {
        // Show as integer if no decimal part
        return if (value == Math.floor(value) && !value.isInfinite()) {
            value.toLong().toString()
        } else {
            value.toBigDecimal().stripTrailingZeros().toPlainString()
        }
    }

    private fun resetState() {
        currentInput.clear()
        firstOperand = 0.0
        pendingOperator = ""
        isNewOperation = false
    }
}