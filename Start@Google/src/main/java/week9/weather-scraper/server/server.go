package server

import (
	"fmt"
	"main/data/date_and_time"
	"main/data/open_weather"
	"main/data/weather_atlas"
	"math"
	"strconv"
)

var MAX_DAYS_FORECAST = 7

func Server() {
	fmt.Println("Inside Server")
	fmt.Println()

	date_and_time.DateAndTime()
	open_weather.OpenWeather()
	weather_atlas.WeatherAtlas()
}

func GetWeatherSummary(city string) map[string]string {
	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)

	var weatherInfoForToday map[string]string = make(map[string]string)
	weatherInfoForToday["MaxTemperature"] = calcAvg(weatherInfo_dateAndTime["MaxTemperature"][0], weatherInfo_weatherAtlas["MaxTemperature"][0], weatherInfo_openWeather["MaxTemperature"][0])
	weatherInfoForToday["MinTemperature"] = calcAvg(weatherInfo_dateAndTime["MinTemperature"][0], weatherInfo_weatherAtlas["MinTemperature"][0], weatherInfo_openWeather["MinTemperature"][0])
	weatherInfoForToday["Humidity"] = calcAvg(weatherInfo_dateAndTime["Humidity"][0], weatherInfo_weatherAtlas["Humidity"][0], weatherInfo_openWeather["Humidity"][0])
	weatherInfoForToday["Wind"] = calcAvg(weatherInfo_dateAndTime["Wind"][0], weatherInfo_weatherAtlas["Wind"][0], weatherInfo_openWeather["Wind"][0])
	weatherInfoForToday["Chance"] = calcAvg(weatherInfo_dateAndTime["Chance"][0], weatherInfo_weatherAtlas["Chance"][0], weatherInfo_openWeather["Chance"][0])

	return weatherInfoForToday
}

func GetTempArray(city string, days int) ([]string, []string) {
	if !validDays(days) {
		return nil, nil
	}

	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)

	var minTemp, maxTemp []string

	// loop through all the days
	for i := 0; i < days; i++ {
		// get avg of minTemp for the i-th day ...
		minTemp = append(minTemp, calcAvg(weatherInfo_dateAndTime["MinTemperature"][i], weatherInfo_weatherAtlas["MinTemperature"][i], weatherInfo_openWeather["MinTemperature"][i]))
		// get avg of maxTemp for the i-th day ...
		maxTemp = append(maxTemp, calcAvg(weatherInfo_dateAndTime["MaxTemperature"][i], weatherInfo_weatherAtlas["MaxTemperature"][i], weatherInfo_openWeather["MaxTemperature"][i]))
	}

	return maxTemp, minTemp
}

func GetAvgTemp(city string, days int) string {
	if !validDays(days) {
		return "-1"
	}

	maxTempArray, minTempArray := GetTempArray(city, days)

	var array []string
	array = append(array, minTempArray...)
	array = append(array, maxTempArray...)

	return calcAvg(array...)
}

func GetNextRainDay(city string) int {
	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)
	_ = weatherInfo_openWeather

	willRainInXDays := -1
	for i := 0; i < MAX_DAYS_FORECAST; i++ {
		chanceOfRangeAVG := calcAvg(weatherInfo_dateAndTime["Chance"][i], weatherInfo_weatherAtlas["Chance"][i])
		if parseInt(chanceOfRangeAVG) > 50 {
			willRainInXDays = i
			break
		}
	}

	return willRainInXDays
}

func GetChanceOfRainInXDays(city string, days int) string {
	if !validDays(days) {
		return "-1"
	}

	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)
	_ = weatherInfo_openWeather

	rainChanceInXDays := calcAvg(weatherInfo_dateAndTime["Chance"][days-1], weatherInfo_weatherAtlas["Chance"][days-1])

	return rainChanceInXDays + "%"
}

func GetSunriseAndSunsetInXDays(city string, days int) (string, string) {
	if !validDays(days) {
		return "-1", "-1"
	}

	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)
	_ = weatherInfo_openWeather

	return weatherInfo_dateAndTime["Sunrise"][days-1], weatherInfo_weatherAtlas["Sunset"][days-1]
}

func GetHighestUvDay(city string) (int, int) {
	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)

	highestUvAvrage := 0
	day := 0
	for i := 0; i < MAX_DAYS_FORECAST; i++ {
		currUvAvrage := parseInt(calcAvg(weatherInfo_dateAndTime["UV"][i], weatherInfo_weatherAtlas["UV"][i], weatherInfo_openWeather["UV"][i]))
		if highestUvAvrage < currUvAvrage {
			highestUvAvrage = currUvAvrage
			day = i
		}
	}

	return highestUvAvrage, day + 1
}

func GetTheLowestWindyDay(city string) (int, int) {
	weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather := scrape(city)

	lowestWindAvg := math.MaxInt64
	day := 0
	for i := 0; i < MAX_DAYS_FORECAST; i++ {
		currWindAvrage := parseInt(calcAvg(weatherInfo_dateAndTime["Wind"][i], weatherInfo_weatherAtlas["Wind"][i], weatherInfo_openWeather["Wind"][i]))
		if lowestWindAvg > currWindAvrage {
			lowestWindAvg = currWindAvrage
			day = i
		}
	}

	return lowestWindAvg, day + 1
}

// ------------- Private Functions -------------

func calcAvg(elm ...string) string {
	// declare a sum variable
	sum := 0
	// loop and sum the elements
	for i := 0; i < len(elm); i++ {
		sum += parseInt(elm[i])
	}
	// return the value divided by the length of elm array
	return strconv.Itoa(sum / len(elm))
}

func parseInt(str string) int {
	toInt, _ := strconv.Atoi(str)
	return toInt
}

func validDays(days int) bool {
	if days < 1 || days > MAX_DAYS_FORECAST {
		fmt.Println("Amount of days can not be negative (<1) or greater than " + fmt.Sprint(MAX_DAYS_FORECAST))
		return false
	}
	return true
}

func scrape(city string) (map[string][]string, map[string][]string, map[string][]string) {
	weatherInfo_dateAndTime := date_and_time.ScrapeTimeAndDate(city)
	weatherInfo_weatherAtlas := weather_atlas.ScrapeWeatherAtlas(city)
	weatherInfo_openWeather := open_weather.ScrapeOpenWeatherMap(city)

	return weatherInfo_dateAndTime, weatherInfo_weatherAtlas, weatherInfo_openWeather
}
