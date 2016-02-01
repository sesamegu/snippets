package com.mikegu.btrace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author haiquan.guhq
 * @version $Id: TestHeapOOM.java, v 0.1 2015年5月21日 下午2:08:24 mike Exp $
 */
public class TestHeapOOM {

	private List<String> list = new ArrayList<String>();

	public void add(String one) {
		list.add(one);
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		TestHeapOOM testHeapOOM = new TestHeapOOM();

		for (int i = 0;; i++) {
			testHeapOOM.add("abc" + i);
			TimeUnit.MILLISECONDS.sleep(1);
		}

	}

}
