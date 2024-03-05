package com.pragyan_space.dualcalc.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Stack

class MainViewModel: ViewModel() {
    var cal1Editor=MutableLiveData<String>()
    var cal1Total=MutableLiveData<String>()

    var cal2Editor=MutableLiveData<String>()
    var cal2Total=MutableLiveData<String>()

    fun calculate(s: String) {
        val len = s.length
        var sign = 1.0
        var result = 0.0
        val stack = Stack<Double>()
        var i = 0
        while (i < len) {
            if (Character.isDigit(s[i])) {
                var sum = s[i].code - '0'.code
                while (i + 1 < len && Character.isDigit(s[i + 1])) {
                    sum = sum * 10 + s[i + 1].code - '0'.code
                    i++
                }
                result += sum * sign
            } else if (s[i] == '+') sign = 1.0 else if (s[i] == '-') sign = -1.0 else if (s[i] == '(') {
                stack.push(result)
                stack.push(sign)
                result = 0.0
                sign = 1.0
            } else if (s[i] == ')') {
                result = result * stack.pop() + stack.pop()
            }
            i++
        }
        cal1Total.postValue(result.toString())
    }
}