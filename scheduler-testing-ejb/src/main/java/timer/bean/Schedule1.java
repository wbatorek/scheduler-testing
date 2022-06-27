package timer.bean;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;

@Startup
@Singleton
public class Schedule1 extends AbstractDcomTimer {

	
	@Override
	public String getTimerName() {
		return "Scheduler 1 timer";
	}

	@Override
	public long getTimerInterval() {
		return 10000;
	}

	@Override
	public boolean isPersistent() {
		return true;
	}
	
	@Override
	public String getJobName() {
		return "Run test scheduled job";
	}

	@Override
	public void run(Timer timer) throws Exception {
		System.out.println("| Scheduler 1 running");
	}

}
