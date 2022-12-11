package open_weather

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"main/data"
	"net/http"
	"os"
	"strconv"
)

func OpenWeather() {
	fmt.Println("Inside OpenWeather")
}

func ScrapeOpenWeatherMap(city string) map[string][]string {
	lat, lon := convertCityToLngLat(city)
	apiKey := "439d4b804bc8187953eb36d2a8c26a02"
	url := fmt.Sprintf("https://openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&units=metric&appid=%s", fmt.Sprint(lat), fmt.Sprint(lon), apiKey)
	response, err := http.Get(url)
	if err != nil {
		fmt.Print(err.Error())
		os.Exit(1)
	}
	responseData, err := ioutil.ReadAll(response.Body)
	if err != nil {
		log.Fatal(err)
	}

	var dataStructure data.OpenWeatherMap
	json.Unmarshal(responseData, &dataStructure)

	var conditionArr []data.WeatherCondition

	var conditionHumidity data.WeatherCondition
	var conditionUV data.WeatherCondition
	var conditionTempMin data.WeatherCondition
	var conditionTempMax data.WeatherCondition
	var conditionWind data.WeatherCondition
	var conditionChance data.WeatherCondition
	var conditionWeatherName data.WeatherCondition
	conditionTempMin.Name = "MinTemperature"
	conditionTempMax.Name = "MaxTemperature"
	conditionArr = append(conditionArr, conditionTempMax)
	conditionArr = append(conditionArr, conditionTempMin)
	conditionHumidity.Name = "Humidity"
	conditionUV.Name = "UV"
	conditionArr = append(conditionArr, conditionHumidity)
	conditionArr = append(conditionArr, conditionUV)
	conditionWind.Name = "Wind"
	conditionChance.Name = "Chance"
	conditionArr = append(conditionArr, conditionWind)
	conditionArr = append(conditionArr, conditionChance)
	conditionWeatherName.Name = "Weather"
	conditionArr = append(conditionArr, conditionWeatherName)

	for i := 0; i < 7; i++ {
		conditionArr[0].Values = append(conditionArr[0].Values, strconv.FormatFloat(dataStructure.Daily[i].Temp.Max, 'f', 0, 64))
		conditionArr[1].Values = append(conditionArr[1].Values, strconv.FormatFloat(dataStructure.Daily[i].Temp.Min, 'f', 0, 64))
		conditionArr[2].Values = append(conditionArr[2].Values, strconv.Itoa(dataStructure.Daily[i].Humidity))
		conditionArr[3].Values = append(conditionArr[3].Values, strconv.FormatFloat(dataStructure.Daily[i].Uvi, 'f', 0, 64))
		conditionArr[4].Values = append(conditionArr[4].Values, strconv.FormatFloat(dataStructure.Daily[i].WindSpeed, 'f', 0, 64))
		conditionArr[5].Values = append(conditionArr[5].Values, strconv.Itoa(dataStructure.Daily[i].Pop))
		conditionArr[6].Values = append(conditionArr[6].Values, dataStructure.Daily[i].Weather[0].Main)

	}
	var weatherInfoPerDay map[string][]string = data.TransformArrayToMap(conditionArr)

	return weatherInfoPerDay
}

func convertCityToLngLat(city string) (float64, float64) {
	apiKey := "439d4b804bc8187953eb36d2a8c26a02"
	url := fmt.Sprintf("https://openweathermap.org/data/2.5/find?q=%s&appid=%s&units=metric", city, apiKey)
	response, err := http.Get(url)
	if err != nil {
		fmt.Print(err.Error())
		os.Exit(1)
	}
	responseData, err := ioutil.ReadAll(response.Body)
	if err != nil {
		log.Fatal(err)
	}
	var data data.TempCordsHolder
	json.Unmarshal(responseData, &data)
	return data.List[0].Coord.Lat, data.List[0].Coord.Lon
}