package com.istio.bookinfo.rests;

public class NeededTools {

	public static void waitForSometime(long millis) {
		System.out.println(Thread.currentThread().getName()+" waiting for "+millis+" millis now.");
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()+" "+e);
		}
	}

}
