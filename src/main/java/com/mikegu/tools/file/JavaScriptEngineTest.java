package com.mikegu.tools.file;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author haiquan.guhq
 * @version $Id: JavaScriptEngineTest.java, v 0.1 2015年4月29日 下午3:35:13 mike Exp $
 */
public class JavaScriptEngineTest {

	private static ScriptEngineManager sem = new ScriptEngineManager();
	private static AtomicInteger count = new AtomicInteger(0);

	public static void multiThreadRun() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 4; i++) {
			newFixedThreadPool.execute(new WorkThread(i));
		}
	}

	static class WorkThread implements Runnable {

		private int flag = 0;

		WorkThread(int flag) {
			this.flag = flag;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			for (int i = 0;; i++) {
				count.getAndIncrement();
				ScriptEngine engine = sem.getEngineByName("JavaScript");
				engine.put("reject", (i + 1) + "");
				engine.put("success", "300");
				engine.put("black_1", flag + "");
				engine.put("black_2", "1");
				System.out.println(engine.toString());
				try {
					engine.eval("reject < " + i + " && success == 300 && (black_1 == 0 || black_2 == 1)");
				} catch (ScriptException e) {
				}

				if (i % 1000 == 0 && flag == 0) {
					System.out.println("count=" + count.get() + "\t time=" + (System.currentTimeMillis() - start));
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws ScriptException
	 */
	public static void main(String[] args) throws ScriptException {
		multiThreadRun();
		// System.out.println(sef.getEngineName());

		// ScriptEngineManager sem = new ScriptEngineManager();
		for (ScriptEngineFactory sef : sem.getEngineFactories()) {
			System.out.println(sef.getEngineName());
		}
		//
		//
		// ScriptEngine se = sem.getEngineByName("JavaScript");
		// System.out.println(se.toString());
		// long start = System.currentTimeMillis();
		// for (int i = 0;; i++) {
		// // ScriptEngine se = sem.getEngineByName("JavaScript");
		// // System.out.println(se.toString());
		// // ScriptEngine se2 = sem.getEngineByName("JavaScript");
		// // System.out.println(se2.toString());
		// ScriptEngine engine = sem.getEngineByName("JavaScript");
		// engine.put("reject", (i + 1) + "");
		// engine.put("success", "300");
		// engine.put("black_1", "0");
		// engine.put("black_2", "1");
		// Object ret = engine
		// .eval("reject < " + i
		// + " && success == 300 && (black_1 == 0 || black_2 == 1)");
		// // System.out.println(i);
		//
		// if (i % 1000 == 0) {
		// System.out.println("i=" + i + "\t time=" + (System.currentTimeMillis() - start));
		// }
		// }

	}

}
