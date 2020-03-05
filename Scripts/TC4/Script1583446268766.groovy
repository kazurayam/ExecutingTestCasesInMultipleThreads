import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def log(status) {
	println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
}

def execute(ExecutorService service, int numThreads, Closure cls, String description) {
	println description
	long l = System.currentTimeMillis()
	(0..<numThreads).each { i ->
		service.execute(cls)
		Thread.sleep(20)
	}
	service.shutdown()
	log 'awaiting'
	service.awaitTermination(1, TimeUnit.MINUTES)
	log 'await completed'
}

execute(Executors.newFixedThreadPool(3), 3, {
	log 'started'
	WebUI.openBrowser('https://katalon-demo-cura.herokuapp.com/')
	WebUI.delay(5)
	WebUI.closeBrowser()
	log 'finished'
},'newFixedThreadPool(5)')
