import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.js.Math

/**
 * Created by hardik.trivedi on 26/10/17.
 */
fun main(args: Array<String>) {
    createUserInput()
}

fun createUserInput() {
    val root = document.getElementById("container")
    root?.appendChild(getInputDiv())
}

fun getInputDiv(): HTMLDivElement {
    val inputDiv = document.create.div {
        label(classes = "primaryText") {
            +"Enter zip code : "
            input {
                id = "zipCode"
                type = InputType.number
                value = 411021.toString()
            }
        }
        button(classes = "getWeatherButton") {
            +"Get Weather"
            type = ButtonType.button
            onClickFunction = {
                val zipCode = document.getElementById("zipCode") as HTMLInputElement
                val xmlHttpRequest = XMLHttpRequest()
                xmlHttpRequest.open("GET", FULL_URL + zipCode.value, false)
                xmlHttpRequest.send()
                val forecastResult = JSON.parse<ForecastResult>(xmlHttpRequest.responseText)
                showData(forecastResult)
            }
        }
    }
    return inputDiv
}

fun showData(forecastResult: ForecastResult) {
    val root = document.getElementById("container")
    root?.appendChild(document.create.div(classes = "currentTemp") {
        h4 {
            +"Weather info for ${forecastResult.city.name}, (${forecastResult.city.country})"
        }
    })
    showForecast(forecastResult)
}

fun showForecast(forecastResult: ForecastResult) {
    val weatherContainer = document.create.div()
    forecastResult.list.forEachIndexed { index, forecast ->
        with(forecast)
        {
            weatherContainer.appendChild(document.create.div(classes = "weatherBlock") {
                div {
                    p(classes = "primaryLightText") {
                        +dt.getFullWeekDay()
                    }
                    p(classes = "secondaryLightText") {
                        +dt.getShortDate()
                    }
                    p(classes = "currentTemp") {
                        +"${Math.round(temp.day)} °C"
                    }
                }
                img(classes = "weatherImage") {
                    src = "images/weather_img.png"
                }
                div {
                    span(classes = "secondaryText") {
                        +weather[0].main
                    }
                }
                div {
                    with(temp) {
                        span(classes = "tipText") { +"max " }
                        span(classes = "primaryText") { +"${Math.round(max)} °C" }
                        span(classes = "secondaryText") { +" /${Math.round(min)} °C" }
                        span(classes = "tipText") { +" min" }
                    }
                }
                onClickFunction = {
                    showDetailedForecast(forecast = forecast, city = forecastResult.city)
                }
            })
        }
    }
    document.getElementById("container")?.appendChild(weatherContainer)
}

fun showDetailedForecast(city: City, forecast: Forecast) {
    val root = document.getElementById("container")
    val weatherDetailDiv = document.create.div(classes = "detailsContainer")
    val basicDetailDiv = document.create.div {
        p(classes = "secondaryText") {
            +"${city.name}, ${city.country} (${city.coord.lat},${city.coord.lon})"
        }
        p(classes = "secondaryText") {
            +forecast.dt.getFullDate()
        }
        p(classes = "secondaryText") {
            +"${forecast.weather[0].main}, ${forecast.weather[0].description}"
        }
    }
    val otherDetailsDiv = document.create.div {
        div {
            id = "leftDiv"
            span(classes = "currentTemp") {
                +"${Math.round(forecast.temp.day)} °C"
            }
            img {
                src = "images/weather_img.png"
                width = 90.toString()
                height = 90.toString()
            }
        }
        div {
            id = "rightDiv"
            p(classes = "secondaryText") { +"Pressure: ${forecast.pressure} mb" }
            p(classes = "secondaryText") { +"Humidity: ${forecast.humidity} %" }
            p(classes = "secondaryText") { +"Wind: ${forecast.speed} mph" }
            p(classes = "secondaryText") { +"Cloudiness: ${forecast.clouds} %" }

        }
        div(classes = "clearBoth")
    }

    weatherDetailDiv.appendChild(basicDetailDiv)
    weatherDetailDiv.appendChild(otherDetailsDiv)

    root?.appendChild(weatherDetailDiv)
}