package com.example.bullseye.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {
    private val gameViewModel: GameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gameView = GameView(requireContext(),
            onSaveGame = { saveGame ->
                gameViewModel.saveGameDetails(saveGame)
            },
            onResetGame = {
//                gameViewModel.saveGameDetails(
//                    SaveGame(
//                        Random.nextInt(101),
//                        50,
//                        1,
//                        0,
//                        GameType(listOf(Player("",0)),99)
//                ))
                gameViewModel.resetGameDetails()
            }
        )
        gameViewModel.loadGameDetails()?.let { gameView.loadGame(it) }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    gameView.saveGame()
                    findNavController().navigateUp()
                }
            })
        return gameView
    }
}