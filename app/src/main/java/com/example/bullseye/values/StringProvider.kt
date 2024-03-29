package com.example.bullseye.values

object StringProvider {

    object MainMenu {
        const val newSingle = "New Single Game"
        const val loadGame = "Load Game"
        const val newMulti = "Multi-Play"
        const val about = "About Game"
    }

    const val info = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
            <title>Bull's Eye</title>
            <meta charset="utf-8">
            <style type="text/css">
            html { background: #faeecd; }
            body { color: #000; font: 16px "Arial Rounded MT Bold", Helvetica; }
            h1 { text-align: center; color: rgb(96, 30, 0); font-size: 24px; font-weight: bold; }
            </style>
            </head>
            <body>
            
            <h1>★ Bull's Eye ★</h1>
            
            <p>This is the awesome game of Bull's Eye where you can win points and earn fame by dragging a slider.</p>
            
            <p>Your goal is to place the slider as close as possible to the target value. The closer you are, the more points you score.</p>
            
            <p style="text-align:center"><em>Enjoy!</em></p>
            
            </body>
            </html>
            """

    object MultiPlayer{
        const val round = "Round: "
        const val player = "Player"
    }

    object Game{
        const val prompt = "Put the Bull's Eye as close as you can to: "
        const val minimumValue = "0"
        const val maximumValue = "100"
        const val button = "Hit Me!"
        const val score = "Score: "
        const val round = "Round: "
        const val player = "Player: "
    }

    object Result{
        const val prompt = "Your Result"
        const val player = "Player"
        const val score = "Score"
    }
}