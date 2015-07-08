// TodoMVC with scala.js and ractive - initial version, obviously implemented and formatted to have few LOC - sorry ;-)
package todomvc;

import org.scalajs.dom
import org.scalajs.dom.Element

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExportAll

case class Todo(id: Int, task: String, completed: Boolean)

sealed case class TodoFilter(filter: Todo => Boolean)

object TodoFilter extends Enumeration {
  val ALL = TodoFilter(_ => true)
  val COMPLETED = TodoFilter(_.completed)
  val ACTIVE =TodoFilter(!_.completed)
}

case class World(todos: Seq[Todo], todoFilter: TodoFilter) {
  def addTodos(todos: Todo*)(w: World) = w.copy(todos = w.todos ++ todos)
  def flatMapTodos(f: Todo => Seq[Todo])(w: World): World = w.copy(todos = w.todos.flatMap(f))
  def setFilter(filter: TodoFilter)(w: World): World = w.copy(todoFilter = filter)
}

@JSExportAll
case class Controller(world: World, templateId: String, targetElement: Element) {
   def visibleTodos: Seq[Todo] = world.todos.filter(world.todoFilter.filter)

  def addTodo(task: String): Unit = this.updateWorld(
      world.addTodos(Todo(world.todos.map(todo => todo.id).max + 1, task, true)))

  def removeTodo(id: Int): Unit = this.updateWorld(
      world.flatMapTodos(todo => if (todo.id == id) Seq.empty else Seq(todo)))

  def render(): Unit = {
    targetElement.innerHTML = "JUHUUUUUUUUUUUuuu"
  }

  private def updateWorld(f: World => World): Unit = Controller(f(world), templateId, targetElement).render()
}

object Main extends JSApp {
  def main(): Unit = Controller(
    World(Seq.empty, TodoFilter.ALL),
    "todoMvcTemplate",
    dom.document.getElementById("todoMvcContent")).render()
}
