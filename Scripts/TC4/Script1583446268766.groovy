import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

def log(status) {
	println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
}

def execute(String method, ExecutorService service) {
	println method
	long l = System.currentTimeMillis()
	(0..<5).each { i ->
		service.execute {
			log 'started'
			WebUI.openBrowser('https://katalon-demo-cura.herokuapp.com/')
			WebUI.delay(3)
			WebUI.closeBrowser()
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
