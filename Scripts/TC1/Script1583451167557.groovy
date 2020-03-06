import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.lang.Runnable

/**
 * refered to https://devs.nobushige.net/groovy/15/
 * 
 * @param status
 * @return
 */
def log(status) {
	println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
}

def execute(int numThreads, Closure cls, List<String> urlList) {
	ExecutorService service = Executors.newFixedThreadPool(numThreads) 
	int collateTo = (urlList.size() / numThreads) + 1
	List<List<String>> groups = urlList.collate(collateTo)
	(0..<groups.size()).each { i ->
		service.execute(cls.curry(groups[i]))
		Thread.sleep(20)
	}
	service.shutdown()
	log 'awaiting'
	service.awaitTermination(1, TimeUnit.MINUTES)
	log 'await completed'
}

Closure cls = { urlList ->
	log "Executor started for ${urlList}"
	WebUI.openBrowser('')
	for (url in urlList) {
		WebUI.navigateToUrl(url)
		WebUI.delay(3)
	}
	WebUI.closeBrowser()
	log "Executor finished for ${urlList}"
}

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

// Now let's open URLs in 5 windows of Browser
execute(5, cls, URL_LIST)　　　　　　　　　　　　　　　　　　