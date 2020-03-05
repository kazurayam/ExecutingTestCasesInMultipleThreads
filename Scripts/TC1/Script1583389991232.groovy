import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

def log(status) {
	println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"	
}

def execute(String method, ExecutorService service) {
	println method
	long l = System.currentTimeMillis()
	(0..<10).each { i ->
		service.execute {
			log 'started'
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
