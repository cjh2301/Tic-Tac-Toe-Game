package ch.makery.address.view

import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml


@sfxml
class MenuController() {
  def goGame(): Unit = {
    MainApp.showGame()
  }

  def handleTutorial(): Unit = {
    MainApp.showTutorial()
  }

  def handleQuit(): Unit = {
    System.exit(0)
  }
}