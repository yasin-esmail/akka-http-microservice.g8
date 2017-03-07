package $package$.models
import slick.lifted.Tag
import slick.model.Column
import slick.model.Table

case class GreetingEntity(id: Int, greeting: String)

class Greetings(tag : Tag) extends Table[(Int, String)](tag, "GREETINGS"){
  def id = column[Int]("id",O.PrimaryKey)
  def greeting = column[String]("greeting")
  def * = id ~ greeting <> (GreetingEntity, GreetingEntity.unapply _)
}
