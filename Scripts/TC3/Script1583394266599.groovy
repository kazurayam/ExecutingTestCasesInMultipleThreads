import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


def log(status) {
	println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
}

def execute(String method, ExecutorService service) {
	println method
	long l = System.currentTimeMillis()
	(0..<10).each { i ->
		service.execute {
			log 'started'
			WebUI.callTestCase(findTestCase("TC2-worker"), ["key": i])
			Thread.sleep(100)
			log 'finished'
		}
		Thread.sleep(20)
	}
	service.shutdown()
	log 'awaiting'
	service.awaitTermination(1, TimeUnit.MINUTES)
	log 'await completed'
}

execute('newFixedThreadPool(5)', Executors.newFixedThreadPool(5))
