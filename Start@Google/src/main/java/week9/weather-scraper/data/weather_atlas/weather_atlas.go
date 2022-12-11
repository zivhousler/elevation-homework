package weather_atlas

import (
	"fmt"
	"main/data"
	"strings"

	"github.com/gocolly/colly"
)

func WeatherAtlas() {
	fmt.Println("Inside WeatherAtlas")
}


func ScrapeWeatherAtlas(city string) map[string][]string {
	c := colly.NewCollector()
	var conditionArr []data.WeatherCondition

	var conditionHumidity data.WeatherCondition
	var conditionUV data.WeatherCondition
	var conditionPrecipitation data.WeatherCondition
	var conditionTempMin data.WeatherCondition
	var conditionTempMax data.WeatherCondition
	var conditionWind data.WeatherCondition
	var conditionChance data.WeatherCondition
	var conditionSunrise data.WeatherCondition
	var conditionSunset data.WeatherCondition
	var conditionWeatherName data.WeatherCondition
	conditionTempMin.Name = "MinTemperature"
	conditionTempMax.Name = "MaxTemperature"
	conditionArr = append(conditionArr, conditionTempMax)
	conditionArr = append(conditionArr, conditionTempMin)
	conditionHumidity.Name = "Humidity"
	conditionUV.Name = "UV"
	conditionPrecipitation.Name = "Amount"
	conditionArr = append(conditionArr, conditionHumidity)
	conditionArr = append(conditionArr, conditionUV)
	conditionArr = append(conditionArr, conditionPrecipitation)
	conditionWind.Name = "Wind"
	conditionChance.Name = "Chance"
	conditionArr = append(conditionArr, conditionWind)
	conditionArr = append(conditionArr, conditionChance)
	conditionSunrise.Name = "Sunrise"
	conditionSunset.Name = "Sunset"
	conditionArr = append(conditionArr, conditionSunrise)
	conditionArr = append(conditionArr, conditionSunset)
	conditionWeatherName.Name = "Weather"
	conditionArr = append(conditionArr, conditionWeatherName)

	c.OnHTML(".card-body", func(table *colly.HTMLElement) {
		var minMax []string
		table.ForEach(".col-4", func(index int, row *colly.HTMLElement) {
			if index > 1 && (index+2)%3 == 0 && row.Text != "" && row.Text != " " {
				minMax = append(minMax, strings.ReplaceAll(data.ClearSpeacialChars(row.Text), "C", " "))
			}
		})
		if len(minMax) > 0 {
			for i := 0; i < len(minMax); i++ {
				conditionArr[0].Values = append(conditionArr[0].Values, strings.Split(minMax[i], " ")[0])
				conditionArr[1].Values = append(conditionArr[1].Values, strings.Split(minMax[i], " ")[1])
			}
		}
		table.ForEach(".d-none", func(index int, row *colly.HTMLElement) {
			if index > 3 {
				if index%4 == 0 {
					conditionArr[2].Values = append(conditionArr[2].Values, data.ClearOnlyNumbers(row.Text))
				} else if index%4 == 1 {
					conditionArr[3].Values = append(conditionArr[3].Values, data.ClearOnlyNumbers(row.Text))
				} else if index%4 == 2 {
					conditionArr[4].Values = append(conditionArr[4].Values, data.ClearOnlyNumbers(row.Text))
				} else {
					conditionArr[7].Values = append(conditionArr[7].Values, strings.Split(data.ClearStringWithSpace(row.Text), " ")[0])
					conditionArr[8].Values = append(conditionArr[8].Values, strings.Split(data.ClearStringWithSpace(row.Text), " ")[1])
				}
			}
		})

		flag := true
		table.ForEach("li", func(index int, row *colly.HTMLElement) {
			if index > 8 {
				row.ForEach("span", func(i int, val *colly.HTMLElement) {
					if flag {
						conditionArr[5].Values = append(conditionArr[5].Values, data.ClearOnlyNumbers(val.Text))
						flag = false
					} else {
						conditionArr[6].Values = append(conditionArr[6].Values, data.ClearOnlyNumbers(val.Text))
						flag = true
					}
				})
			}
		})
		table.ForEach(".fs-4", func(index int, span *colly.HTMLElement) {
			if index > 0 {
				conditionArr[9].Values = append(conditionArr[9].Values, span.Text)
			}
		})
	})

	c.OnResponse(func(r *colly.Response) {
		// fmt.Println(r.StatusCode)
		// fmt.Println()
	})

	c.OnRequest(func(r *colly.Request) {
		// fmt.Println()
		// fmt.Println("Visiting", r.URL)
	})

	c.Visit("https://www.weather-atlas.com/en/israel/" + city + "-long-term-weather-forecast")

	var weatherInfoPerDay map[string][]string = data.TransformArrayToMap(conditionArr)

	return weatherInfoPerDay
}