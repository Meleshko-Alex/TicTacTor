package com.strategic.grid.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.strategic.grid.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var combinationList = mutableListOf<Array<Int>>()
    private var boxPositions = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var playerTurn = 1
    private var totalSelectedBoxes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(context, R.color.background)
            navigationBarColor = ContextCompat.getColor(context, R.color.background)
        }

        binding.tvPlayerOne.text = intent.getStringExtra("playerOne")
        binding.tvPlayerTwo.text = intent.getStringExtra("playerTwo")

        initCombinationList()
        setListenersForCells()
    }

    private fun initCombinationList() {
        combinationList.add(arrayOf(0, 1, 2))
        combinationList.add(arrayOf(3, 4, 5))
        combinationList.add(arrayOf(6, 7, 8))
        combinationList.add(arrayOf(0, 3, 6))
        combinationList.add(arrayOf(1, 4, 7))
        combinationList.add(arrayOf(2, 5, 8))
        combinationList.add(arrayOf(2, 4, 6))
        combinationList.add(arrayOf(0, 4, 8))
    }

    private fun setListenersForCells() {
        binding.cell1.setOnClickListener { view ->
            if (isBoxSelectable(0)) performAction(view as ImageView, 0)}
        binding.cell2.setOnClickListener { view ->
            if (isBoxSelectable(1)) performAction(view as ImageView, 1) }
        binding.cell3.setOnClickListener { view ->
            if (isBoxSelectable(2)) performAction(view as ImageView, 2) }
        binding.cell4.setOnClickListener { view ->
            if (isBoxSelectable(3)) performAction(view as ImageView, 3) }
        binding.cell5.setOnClickListener { view ->
            if (isBoxSelectable(4)) performAction(view as ImageView, 4) }
        binding.cell6.setOnClickListener { view ->
            if (isBoxSelectable(5)) performAction(view as ImageView, 5) }
        binding.cell7.setOnClickListener { view ->
            if (isBoxSelectable(6)) performAction(view as ImageView, 6) }
        binding.cell8.setOnClickListener { view ->
            if (isBoxSelectable(7)) performAction(view as ImageView, 7) }
        binding.cell9.setOnClickListener { view ->
            if (isBoxSelectable(8)) performAction(view as ImageView, 8) }
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        return boxPositions[boxPosition] == 0
    }

    private fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross)
            if (checkPlayerWin()) {
                val dialog = WinDialog(
                    this,
                    "${binding.tvPlayerOne.text} has won the match", this
                )
                dialog.setCancelable(false)
                dialog.show()
            } else if (totalSelectedBoxes == 9) {
                val dialog = WinDialog(this, "It is a draw!", this)
                dialog.setCancelable(false)
                dialog.show()
            } else {
                changePlayerTurn(currentPlayerTurn = 2)
                totalSelectedBoxes++
            }
        } else {
            imageView.setImageResource(R.drawable.zero)
            if (checkPlayerWin()) {
                val dialog = WinDialog(
                    this,
                    "${binding.tvPlayerTwo.text} has won the match", this
                )
                dialog.setCancelable(false)
                dialog.show()
            } else if (selectedBoxPosition == 9) {
                val dialog = WinDialog(this, "It is a draw!", this)
                dialog.setCancelable(false)
                dialog.show()
            } else {
                changePlayerTurn(currentPlayerTurn = 1)
                totalSelectedBoxes++
            }
        }
    }

    private fun changePlayerTurn(currentPlayerTurn: Int) {
        playerTurn = currentPlayerTurn

        val playerOneBackground = if (playerTurn == 1) R.drawable.player_card else R.drawable.cell
        val playerTwoBackground = if (playerTurn != 1) R.drawable.player_card else R.drawable.cell

        binding.llPlayerOne.setBackgroundResource(playerOneBackground)
        binding.llPlayerTwo.setBackgroundResource(playerTwoBackground)
    }

    private fun checkPlayerWin(): Boolean {
        for (i in 0 until combinationList.size) {
            val combination = combinationList[i]
            if (boxPositions[combination[0]] == playerTurn
                && boxPositions[combination[1]] == playerTurn
                && boxPositions[combination[2]] == playerTurn
            ) {
                return true
            }
        }
        return false
    }

    fun restartMatch() {
        boxPositions = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        playerTurn = 1
        totalSelectedBoxes = 1
        binding.cell1.setImageResource(R.drawable.transparent_back)
        binding.cell2.setImageResource(R.drawable.transparent_back)
        binding.cell3.setImageResource(R.drawable.transparent_back)
        binding.cell4.setImageResource(R.drawable.transparent_back)
        binding.cell5.setImageResource(R.drawable.transparent_back)
        binding.cell6.setImageResource(R.drawable.transparent_back)
        binding.cell7.setImageResource(R.drawable.transparent_back)
        binding.cell8.setImageResource(R.drawable.transparent_back)
        binding.cell9.setImageResource(R.drawable.transparent_back)
    }
}