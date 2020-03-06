package com.kazurayam.ks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

public class ThreadedURLVisitor {

	static def log(status) {
		println "[${Thread.currentThread().name}][${new Date().format('HH:mm:ss.SSS')}] ${status}"
	}

	static void execute(int numThreads, List<String> urlList, Closure closure) {
		ExecutorService service = Executors.newFixedThreadPool(numThreads)
		int collateToSize = getCollateToSize(urlList, numThreads)
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
	
	static private getCollateToSize(List list, int divisor) {
		if (list.size() % divisor == 0) {
			return list.size() / divisor
		} else {
			return list.size() / divisor + 1
		}
	} 
}
