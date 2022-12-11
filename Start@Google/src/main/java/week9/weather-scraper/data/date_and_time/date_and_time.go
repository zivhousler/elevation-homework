package date_and_time

import (
	"fmt"
	"main/data"
	"strings"

	"github.com/gocolly/colly"
)

func DateAndTime() {
	fmt.Println("Inside DateAndTime")
}

func ScrapeTimeAndDate(city string) map[string][]string {
	c := colly.NewCollector()

	var conditionArr []data.WeatherCondition

	c.OnHTML("table#wt-ext > thead", func(tr *colly.HTMLElement) {
		var condition data.WeatherCondition
		tr.ForEach("th", func(index int, th *colly.HTMLElement) {
			if th.Text != "" && index > 6 && index != 11 {
				if th.Text == "Temperature" {
					var conditionTempMin data.WeatherCondition
					var conditionTempMax data.WeatherCondition
					conditionTempMin.Name = "MinTemperature"
					conditionTempMax.Name = "MaxTemperature"
					conditionArr = append(conditionArr, conditionTempMax)
					conditionArr = append(conditionArr, conditionTempMin)
				} else {
					condition.Name = th.Text
					conditionArr = append(conditionArr, condition)
				}
			}
		})
	})

	c.OnHTML("table#wt-ext > tbody", func(tr *colly.HTMLElement) {
		counter := 0
		tr.ForEach("td", func(index int, td *colly.HTMLElement) {
			if index%12 == 1 {
				text := strings.ReplaceAll(td.Text[:len(td.Text)-5], " ", "")
				maxMinTempArray := strings.Split(text, "/")
				conditionArr[counter%11].Values = append(conditionArr[counter%11].Values, maxMinTempArray[0])
				conditionArr[counter%11+1].Values = append(conditionArr[counter%11+1].Values, maxMinTempArray[1])
				counter += 2
			} else if index%12 != 0 && (index+7)%12 != 0 {
				currText := td.Text
				if index%12 != 2 {
					currText = data.ClearString(currText)
				}
				if index%12 == 8 {
					if len(currText) == 0 {
						currText = "0"
					}
				}
				conditionArr[counter%11].Values = append(conditionArr[counter%11].Values, currText)
				counter++
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

	c.Visit("https://www.timeanddate.com/weather/israel/" + city + "/ext")

	var weatherInfoPerDay map[string][]string = data.TransformArrayToMap(conditionArr)
	return weatherInfoPerDay
}
