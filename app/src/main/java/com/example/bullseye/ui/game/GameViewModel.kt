package com.example.bullseye.ui.game

import androidx.lifecycle.ViewModel
import com.example.bullseye.model.SaveGame

class GameViewModel(private val gamePreferences: GamePreferences) : ViewModel() {

    fun saveGameDetails(saveGame: SaveGame) {
        gamePreferences.saveSaveGame(saveGame)
    }

    fun loadGameDetails(): SaveGame? {
        return gamePreferences.getSaveGame()
    }

    fun resetGameDetails(){
        gamePreferences.resetSaveGame()
    }
}