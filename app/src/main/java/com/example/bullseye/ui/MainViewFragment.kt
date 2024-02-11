package com.example.bullseye.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bullseye.R
import com.example.bullseye.model.GameType
import com.example.bullseye.model.Player
import com.example.bullseye.model.SaveGame
import com.example.bullseye.ui.game.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainViewFragment : Fragment() {
    private val gameViewModel: GameViewModel by viewModel()
    private lateinit var mainView: MainView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = MainView(
            requireContext(),
            onNewGame = {
                gameViewModel.saveGameDetails(
                    SaveGame(
                        Random.nextInt(101),
                        50,
                        1,
                        0,
                        GameType(listOf(Player("You", 0)), 99)
                    )
                )
                findNavController().navigate(R.id.gameFragment)
            }
        )
        return mainView
    }

    override fun onResume() {
        super.onResume()
        println("FUCK ${gameViewModel.loadGameDetails()}")
        mainView.haveLoadGame(gameViewModel.loadGameDetails() != null)
    }

}