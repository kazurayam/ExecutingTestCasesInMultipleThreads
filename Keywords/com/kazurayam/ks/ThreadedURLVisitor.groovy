package com.kazurayam.ks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

public class ThreadedURLVisitor {
	
	static def log(status) {
		println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
	}
	
	static void execute(int numThreads, Closure closure, List<String> urlList) {
		ExecutorService service = Executors.newFixedThreadPool(numThreads)
		int collateToSize = (urlList.size() / numThreads) + 1
		List<List<String>> groups = urlList.collate(collateToSize)
		(0..<groups.size()).each { i ->
			service.execute(closure.curry(groups[i]))
			Thread.sleep(20)
		}
		service.shutdown()
		log 'awaiting'
		service.awaitTermination(1, TimeUnit.MINUTES)
		log 'await completed'
	}
}
