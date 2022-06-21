package simulation.common

import java.io.IOException
import java.util.Properties

class PropertiesLoader {
  val properties: Properties = loadProperties()

  def getProperty(key: String): String = {
    properties.getProperty(key)
  }

  def loadProperties(): Properties = {
    val p = new Properties
    val loader = Thread.currentThread.getContextClassLoader
    val stream = loader.getResourceAsStream("performance.properties")
    try p.load(stream)
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
    p
  }

  def get(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }
}

object PropertiesLoader {
  def get(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }
}
