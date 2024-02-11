package com.example.bullseye.ui.multiPlayerMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bullseye.model.SaveGame
import com.example.bullseye.ui.game.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MultiPlayerMenuFragment : Fragment() {
    private val gameViewModel: GameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MultiPlayerMenuView(requireContext(), onGameStart = {
            gameViewModel.saveGameDetails(
                SaveGame(
                    Random.nextInt(101),
                    50,
                    1,
                    0,
                    it
                )
            )
        })
    }

}