package timer.bean;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.inject.Inject;

@Singleton
@Startup
public class QueueSchedulerTimer extends AbstractDcomTimer {

//	 private static final Logger logger = LogManager.getLogger(QueueSchedulerTimer.class);

	@Inject
	QueueScheduler queueScheduler;
	
	@Override
	public String getJobName() {
		return "checkForNewMessages";
	}

	@Override
	public String getTimerName() {
		return "QueueSchedulerTimer";
	}

	@Override
	public long getTimerInterval() {
		return 1000;
	}

	@Override
	public boolean isPersistent() {
		return true;
	}

	@Override
	public void run(Timer timer) throws Exception {
		queueScheduler.checkForNewMessagesProgramaticTimer();
		if (System.currentTimeMillis() % 10 == 0) {
			throw new RuntimeException("Runtime exception inside Queue Scheduler");

		}

	}



}
