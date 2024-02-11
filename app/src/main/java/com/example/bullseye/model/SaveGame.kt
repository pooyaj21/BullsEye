package com.example.bullseye.model

data class SaveGame(
    val targetNumber: Int,
    val scrollPosition: Int,
    val currentRound: Int,
    val currentPlayer: Int,
    val gameType: GameType
)