package vn.edu.hust.student.dynamicpool.dal.executor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceFactory {

	private static final int poolSize = 32;
	private static final int scheduledCount = 2;
	private static ExecutorServiceFactory _instance;

	public static final ExecutorServiceFactory getInstance() {
		if (_instance == null) {
			_instance = new ExecutorServiceFactory();
		}
		return _instance;
	}

	private ExecutorService executorService;
	private ScheduledExecutorService scheduledExecutorService;

	private ExecutorServiceFactory() {
		executorService = new ThreadPoolExecutor(poolSize, poolSize, 60,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				new ThreadFactory() {
					final AtomicInteger threadNumber = new AtomicInteger(1);

					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "ThreadPool-"
								+ threadNumber.getAndIncrement());
					}
				});

		scheduledExecutorService = new ScheduledThreadPoolExecutor(
				scheduledCount, new ThreadFactory() {
					final AtomicInteger threadNumber = new AtomicInteger(1);

					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "Scheduled-"
								+ threadNumber.getAndIncrement());
					}
				});
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public ScheduledExecutorService getScheduledExecutorService() {
		return scheduledExecutorService;
	}

	public void execute(Runnable runnable) {
		this.getExecutorService().execute(runnable);
	}

	private static Map<Integer, ScheduledFuture<?>> scheduleIdMapping = new ConcurrentHashMap<Integer, ScheduledFuture<?>>();
	private static int scheduleIdSeed = 0;

	private static int generateScheduleId() {
		return scheduleIdSeed++;
	}

	public ScheduledFuture<?> scheduleExecution(final int delay,
			final int times, final Runnable runnable) {
		if (times == 0)
			return null;
		final Integer id = generateScheduleId();

		scheduleIdMapping.put(id, this.getScheduledExecutorService()
				.scheduleAtFixedRate(new Runnable() {
					private int runningCount = 0;

					@Override
					public void run() {
						if (times > 0) {
							this.runningCount++;
							if (this.runningCount > times) {
								scheduleIdMapping.get(id).cancel(true);
								return;
							}
						}
						execute(runnable);
					}
				}, delay, delay, TimeUnit.MILLISECONDS));
		return scheduleIdMapping.get(id);
	}
}
