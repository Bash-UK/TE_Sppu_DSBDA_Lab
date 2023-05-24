import scala.io.Source

object WeatherProcessor {
  def main(args: Array[String]): Unit = {
    val filename = "weatherHistory.csv"

    val lines = Source.fromFile(filename).getLines()


    // Map
    val data = lines.map(line => {
      val fields = line.split(",")
      val temperature = fields(3).toDouble
      val dewPoint = fields(4).toDouble
      val windSpeed = fields(6).toDouble
      (temperature, dewPoint, windSpeed)
    })


    // Reduce
    val (tempSum, dewPointSum, windSpeedSum, count) = data.foldLeft((0.0, 0.0, 0.0, 0)) {
      case ((tempAcc, dewPointAcc, windSpeedAcc, countAcc), (temperature, dewPoint, windSpeed)) =>
        (tempAcc + temperature, dewPointAcc + dewPoint, windSpeedAcc + windSpeed, countAcc + 1)
    }

    val avgTemp = tempSum / count
    val avgDewPoint = dewPointSum / count
    val avgWindSpeed = windSpeedSum / count

    println(s"Average temperature: $avgTemp")
    println(s"Average dew point: $avgDewPoint")
    println(s"Average wind speed: $avgWindSpeed")
  }
}
