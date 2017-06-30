package $package$.db

import $package$.models.GreetingEntity

trait MessageDatabaseModule {
	self : Profile => 
	import profile.api._

	class GreetingTable(tag : Tag) extends Table[GreetingEntity](tag, "GREETINGS"){
	  def id = column[Int]("id",O.PrimaryKey)
	  def greeting = column[String]("greeting")
	  def * = (id, greeting) <> (GreetingEntity.tupled, GreetingEntity.unapply)
	}

	val greetings = TableQuery[GreetingTable]

	def createGreetingSchema = exec(greetings.schema.create)
	def insertGreeting(greeting : Greeting)  = exec(greetings += greeting)
	def insertGreetings(greeting : Seq[Greeting]) = exec(this.greetings ++= greeting)
	def selectGreeting(id : Long) = exec(messages.filter(_.id === id).result)
	def selectGreeting() = exec(messages.result)

}