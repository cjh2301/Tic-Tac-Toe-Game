package ch.makery.address.view

import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml


@sfxml
class TutorialController(){
  def backToMenu(): Unit = {
    MainApp.showMenu()
  }
}