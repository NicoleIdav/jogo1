package com.example.jogobruno

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //vetor bidimensional que representará o tabuleiro no jogo
    val tabuleiro = arrayOf(
        arrayOf("A","B","C"),
        arrayOf("D","E","F"),
        arrayOf("G","H","F")
    )

    //Qual jogador está jogando
    var jogadorAtual = "x"

    // Método onCreate que é chamado quando a Activity é criada
    // Entrada: savedInstanceState - o estado salvo da Activity
    // Saída: Nenhuma
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    //Função que será chamada quando um botão for clicado
    //Entrada: A view que foi clicada
    //Saída: Nenhuma
    fun buttonClick(view: View) {
        // Converte a view recebida para um botão
        val buttonSelecionado = view as Button
        //O texto do botão recebe o jogador atual
        buttonSelecionado.text = "X"
        //Este comando irá definir um background pro botão
        buttonSelecionado.setBackgroundResource(R.drawable.b2)
        // Desativa o botão para que não possa ser clicado novamente
        buttonSelecionado.isEnabled = false

        //De acordo com o botão clicado, a posição da matriz receberá o jogador e adiciona o "x" na posição selecionada
        when (buttonSelecionado.id) {
            binding.buttonUm.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonNove.id -> tabuleiro[2][2] = jogadorAtual
        }

        //As variáveis geram posições randômicas para definir o movimento do computador
        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)
        //Tenta encontrar uma posição vazia no tabuleiro
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)
            // O programa verifica se a posição escolhida já foi preenchida, sai do loop se encontrar uma posição vazia
            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                break
            }

            i++
        }
        //Marca a posição encontrada como "O"
        tabuleiro[rX][rY] = "O"
        // A máquina seleciona um número randomizado a partir da função abaixo, e a preenche
        val posicao = rX * 3 + rY


        when (posicao) {
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.b3)
                binding.buttonUm.isEnabled = false
            }

            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.b3)
                binding.buttonDois.isEnabled = false
            }

            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.b3)
                binding.buttonTres.isEnabled = false
            }

            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.b3)
                binding.buttonQuatro.isEnabled = false
            }

            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.b3)
                binding.buttonCinco.isEnabled = false
            }

            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.b3)
                binding.buttonSeis.isEnabled = false
            }

            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.b3)
                binding.buttonSete.isEnabled = false
            }

            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.b3)
                binding.buttonOito.isEnabled = false
            }

            9 -> {
                binding.buttonNove.setBackgroundResource(R.drawable.b3)
                binding.buttonNove.isEnabled = false
            }
        }

        //Recebe o jogador vencedor através da função verificaTabuleiro.
        var vencedor = verificaVencedor(tabuleiro)

        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    //Função que verifca se há vencedor e empate
    //Entrada: o botão que foi selecionado
    //Saída: indicação do resultado do jogo
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        for (i in 0 until 3) {
            //Verifica se há três iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            //Verifica se há três itens iguais na coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[2][2] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }

        //Verifica a quantidade de jogadores
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor.equals("x") || valor.equals("0")) {
                    empate++
                }
            }
        }
        //Se existem 9 jogadas e não há três letras iguais, houve um empate
        if (empate == 9) {
            return "Empate"
        }
        return null
    }


}
