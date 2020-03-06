import com.kazurayam.ks.ThreadedURLVisitor
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Example of visiting a bunch of URLs opening multiple browser windows parallelly
 * 
 */
List<String> URL_LIST = [
	"https://forum.katalon.com/",
	"https://forum.katalon.com/top",
	"https://forum.katalon.com/categories",
	"https://www.google.com/",
	"https://www.google.com/search?sxsrf=ALeKk00mejPHLw61uCpLs_RXe4X-N79fEg%3A1583451410300&source=hp&ei=Eo1hXrn2D6SYr7wPud65kAo&q=katalon&oq=katalon&gs_l=psy-ab.3..35i39l3j0i203l7.22108.22971..23260...3.0..0.103.630.6j1......0....1..gws-wiz.....10..35i362i39j0.nYoAdKEUWmM&ved=0ahUKEwi5x8-WwIToAhUkzIsBHTlvDqIQ4dUDCAY&uact=5",
	"https://www.yahoo.co.jp/",
	"https://finance.yahoo.co.jp/",
	"https://www.47news.jp/",
	"https://www.47news.jp/national",
	"https://www.47news.jp/sports",
	"https://www.47news.jp/world",
	"https://www.who.int/",
	"https://officialhospitality.tokyo2020.org/?lang=ja&gclid=CjwKCAiA44LzBRB-EiwA-jJipEK4i8cS4cf5FMpOtLXMR1RalBGwacM8BiYspjdMMD9OWEGi03cJVhoCcpoQAvD_BwE"
]

Closure closure = { urlList ->
	WebUI.openBrowser('')
	WebUI.setViewPortSize(700,300)
	for (url in urlList) {
		WebUI.navigateToUrl(url)
		// do whatever you want here
		WebUI.delay(3)
	}
	WebUI.closeBrowser()
}

// Now let's visit URLs in 4 browser windows
ThreadedURLVisitor.execute(4, URL_LIST, closure)
