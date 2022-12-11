package main

import (
	"fmt"
	"main/server"
)

var city = "haifa"
var days = server.MAX_DAYS_FORECAST

func main() {
	fmt.Println("Weather forecast for today: " + fmt.Sprint(server.GetWeatherSummary(city)))
	fmt.Println("The temperatures for the next " + fmt.Sprint(days) + " days are " + fmt.Sprint(server.GetTempArray(city, days)) + "(max,min)")
	fmt.Println("The avarage temperature for the next " + fmt.Sprint(days) + " days will be " + fmt.Sprint(server.GetAvgTemp(city, days)) + "°C")

	willRainInXDays := server.GetNextRainDay(city)
	if willRainInXDays == -1 {
		fmt.Println("It is probably not going to rain for the next " + fmt.Sprint(days) + " days!")
	} else {
		fmt.Println("It is going to be rainy in " + fmt.Sprint(willRainInXDays+1) + " days from today")
	}

	fmt.Println("The chance of rain in " + fmt.Sprint(days) + " is going to be " + fmt.Sprint(server.GetChanceOfRainInXDays(city, days)))

	sunrise, sunset := server.GetSunriseAndSunsetInXDays(city, days)
	fmt.Println("The sunrise in " + fmt.Sprint(days) + " days is going to be at " + sunrise + "AM and sunset at " + sunset + "PM")

	highestUvRate, day := server.GetHighestUvDay(city)
	fmt.Println("The highest UV rate in the following week will be " + fmt.Sprint(highestUvRate) + " in " + fmt.Sprint(day) + " days from now")

	lowestWindSpeed, day := server.GetTheLowestWindyDay(city)
	fmt.Println("The least windy day is going to be in " + fmt.Sprint(day) + " days from now, with " + fmt.Sprint(lowestWindSpeed) + "km/h")
}


