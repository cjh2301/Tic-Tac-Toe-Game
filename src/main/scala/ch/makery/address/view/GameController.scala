package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.event.ActionEvent
import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml
import scalafx.scene.text.Text
import scalafx.scene.text.Font


@sfxml
class GameController(
                      private val button1: Button,
                      private val button2: Button,
                      private val button3: Button,
                      private val button4: Button,
                      private val button5: Button,
                      private val button6: Button,
                      private val button7: Button,
                      private val button8: Button,
                      private val button9: Button,
                      private var userText: Text
                    ) {


  private var currentPlayer = "X"
  private var board = Array.fill(3, 3)("")
  private var gameActive = true

  def button1Click(event: ActionEvent): Unit = handleButtonClick(button1, 0, 0)
  def button2Click(event: ActionEvent): Unit = handleButtonClick(button2, 0, 1)
  def button3Click(event: ActionEvent): Unit = handleButtonClick(button3, 0, 2)
  def button4Click(event: ActionEvent): Unit = handleButtonClick(button4, 1, 0)
  def button5Click(event: ActionEvent): Unit = handleButtonClick(button5, 1, 1)
  def button6Click(event: ActionEvent): Unit = handleButtonClick(button6, 1, 2)
  def button7Click(event: ActionEvent): Unit = handleButtonClick(button7, 2, 0)
  def button8Click(event: ActionEvent): Unit = handleButtonClick(button8, 2, 1)
  def button9Click(event: ActionEvent): Unit = handleButtonClick(button9, 2, 2)

  private def handleButtonClick(button: Button, row: Int, col: Int): Unit = {
    if (gameActive && button.text.value.isEmpty) {
      button.text = currentPlayer
      button.font = Font.font("Lucida Sans", 36)
      board(row)(col) = currentPlayer
      button.style = currentPlayer match {
        case "X" => "-fx-background-color: green;"
        case "O" => "-fx-background-color: maroon;"
      }
      if (checkWinner()) {
        userText.text = s"$currentPlayer wins!"
        gameActive = false
      } else if (isDraw()) {
        userText.text = "It's a draw!"
        gameActive = false
      } else {
        togglePlayer()
        userText.text = s"$currentPlayer's turn"
      }
    }
  }

  private def togglePlayer(): Unit = {
    currentPlayer = if (currentPlayer == "X") "O" else "X"
  }

  private def checkWinner(): Boolean = {
    val rows = board.exists(row => row.forall(_ == currentPlayer))
    val cols = board.transpose.exists(col => col.forall(_ == currentPlayer))
    val diag1 = (0 until 3).forall(i => board(i)(i) == currentPlayer)
    val diag2 = (0 until 3).forall(i => board(i)(2 - i) == currentPlayer)
    rows || cols || diag1 || diag2
  }

  private def isDraw(): Boolean = {
    board.flatten.forall(_.nonEmpty)
  }

  def goRestart(event: ActionEvent): Unit = {
    board = Array.fill(3, 3)("")
    button1.text = ""
    button2.text = ""
    button3.text = ""
    button4.text = ""
    button5.text = ""
    button6.text = ""
    button7.text = ""
    button8.text = ""
    button9.text = ""
    button1.style = ""
    button2.style = ""
    button3.style = ""
    button4.style = ""
    button5.style = ""
    button6.style = ""
    button7.style = ""
    button8.style = ""
    button9.style = ""
    currentPlayer = "X"
    userText.text = s"$currentPlayer's turn"
    gameActive = true
  }

  def goMenu(event: ActionEvent): Unit = {
    MainApp.showMenu()
  }
}


