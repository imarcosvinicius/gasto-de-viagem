package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gastodeviagem.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (validationOK()) {

            try {
                val distance = binding.editDistanse.text.toString().toFloat()
                val price = binding.editPrice.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy
                binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"

            } catch (nfe: NumberFormatException) {
                Toast.makeText(
                    this,
                    getString(R.string.informar_valores_validos), Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, getString(R.string.preencha_todos_campos), Toast.LENGTH_LONG)
                .show()
        }


    }

    private fun validationOK(): Boolean {
        return (binding.editDistanse.text.toString() != ""
                && binding.editDistanse.text.toString() != "0"
                && binding.editPrice.text.toString() != ""
                && binding.editPrice.text.toString() != "0"
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString() != "0")

    }
}