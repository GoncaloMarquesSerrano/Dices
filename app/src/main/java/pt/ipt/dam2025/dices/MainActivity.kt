package pt.ipt.dam2025.dices

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pt.ipt.dam2025.dices.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // objeto que fará a ligação aos objetos presentes na interface (XML) da app
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // instanciar o objeto binding
        binding = ActivityMainBinding.inflate(layoutInflater)


        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // se carreguei no butão alguma coisa deveria acontecer
        // findViewById<Button>(R.id.btn_dice_roll).setOnClickListener {  }
        binding.btnDiceRoll.setOnClickListener {
            // rodar o dado
            diceRoll()

        }
    }
}

/**
 * Roda o dado e atualiza a imagem correspondente
 */
private fun MainActivity.diceRoll() {
    /* Algoritmo
     1 - gerar num aleatório entre [1; 6]
     2 - determinar a imagem a mostrar em função do número gerado
     3- mostrar imagem
     4 - atualizar o texto
     5 - atualizar o valor por extenso correspondente ao número gerado
     */

    // 1
    val randomNumber: Int = Random.nextInt(1, 7)

    // 2
    val imageNumber: Int = when(randomNumber){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice
    }

    // 3
    binding.imgDice.setImageResource(imageNumber)

    // 4
    binding.txtViewValueDice.text = randomNumber.toString()

    // 5
    val numberText: Int = when(randomNumber){
        1 -> R.string.n1
        2 -> R.string.n2
        3 -> R.string.n3
        4 -> R.string.n4
        5 -> R.string.n5
        6 -> R.string.n6
        else -> R.string.n
    }

    // atribuir texto à label
    val txt = getString(numberText)
    binding.txtViewValueDiceExtense.text = " ($txt)"
}
