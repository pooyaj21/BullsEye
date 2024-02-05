package com.example.bullseye

import com.example.bullseye.ui.game.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    viewModel { GameViewModel() }
}

