package com.example.bullseye.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bullseye.ui.game.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : Fragment() {
    private val gameViewModel: GameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultView = ResultView(requireContext())
        gameViewModel.loadGameDetails()?.gameType?.playerList?.let { resultView.bindData(it) }
        return resultView
    }
}