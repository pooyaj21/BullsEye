package com.example.bullseye.ui.game

import android.content.Context
import android.content.SharedPreferences
import com.example.bullseye.model.SaveGame
import com.google.gson.Gson

class GamePreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveSaveGame(saveGame: SaveGame) {
        val json = gson.toJson(saveGame)
        sharedPreferences.edit().putString("save_game", json).apply()
    }

    fun getSaveGame(): SaveGame? {
        if (sharedPreferences.getString("save_game", null) == "") return null
        val json = sharedPreferences.getString("save_game", null)
        return gson.fromJson(json, SaveGame::class.java)
    }

    fun resetSaveGame() {
        sharedPreferences.edit().putString("save_game", "").apply()
    }
}
