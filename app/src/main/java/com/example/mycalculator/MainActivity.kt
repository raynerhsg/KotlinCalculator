package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var txtInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.txtInput)

    }


    fun onDigit(view: View) {
        txtInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view: View) {
        txtInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            txtInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        txtInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                txtInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }


    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var prefix = ""
            var txtVal = txtInput?.text.toString()

            try {
                if (txtVal.startsWith("-")) {
                    prefix = "-"
                    txtVal = txtVal.substring(1)
                }
                if (txtVal.contains("-")) {
                    val splitValue = txtVal.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() - two.toDouble()
                    txtInput?.text = removeZeroAfterDot(result.toString())
                } else if (txtVal.contains("+")) {
                    val splitValue = txtVal.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    txtInput?.text = removeZeroAfterDot(result.toString())
                } else if (txtVal.contains("/")) {
                    val splitValue = txtVal.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    txtInput?.text = removeZeroAfterDot(result.toString())
                } else if (txtVal.contains("*")) {
                    val splitValue = txtVal.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    txtInput?.text = removeZeroAfterDot(result.toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }
    }


    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }


}