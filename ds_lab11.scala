import scala.io.Source

val filename = "weatherHistory.csv"
val lines = Source.fromFile(filename).getLines().toList

val (tempSum, dewSum, windSum, count) = lines.foldLeft((0.0, 0.0, 0.0, 0)) {
  case ((tempAcc, dewAcc, windAcc, countAcc), line) =>
    val fields = line.split(",")
    val temp = fields(3).toDouble
    val dewPoint = fields(4).toDouble
    val wind = fields(6).toDouble
    (
      tempAcc + temp,
      dewAcc + dewPoint,
      windAcc + wind,
      countAcc + 1
    )
}

val avgTemp= tempSum/count
val avgDew = dewSum/count
val avgWind = windSum/count
println(s"Average temperature: $avgTemp")
println(s"Average dew point: $avgDew")
println(s"Average wind speed: $avgWind")

