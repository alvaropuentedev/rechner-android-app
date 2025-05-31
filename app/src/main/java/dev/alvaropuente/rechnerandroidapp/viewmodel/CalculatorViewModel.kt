package dev.alvaropuente.rechnerandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.alvaropuente.rechnerandroidapp.domain.useCase.calculatorUseCase

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    fun onButtonClick(btn: String) {
        Log.i("Clicked Button", btn)

        _equationText.value?.let {
            if (btn == "AC") {
                _equationText.value = ""
                _resultText.value = "0"
                return
            }

            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)
                }
                return
            }

            if (btn == "=") {
                _equationText.value = _resultText.value
                return
            }
            // Append Button to Equation
            _equationText.value = it + btn

            //Calculate Result
            try {
                _resultText.value =
                    calculatorUseCase(_equationText.value ?: "", _resultText.value?: "")
            } catch (_: Exception) {
                _resultText.value = "Error"
            }

        }
    }
}