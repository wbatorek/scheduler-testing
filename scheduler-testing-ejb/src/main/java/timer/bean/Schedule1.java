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

//	@Override
//	public void run() {
//		System.out.println("| Scheduler 1 running");
//		
//	}

	
//	@Schedule(second = "*/1", minute = "*", hour = "*")
	public void scheduleTask() {
		System.out.println("|                                           Scheduler 1 running");
	}

	@Override
	public void ejbTimeout(Timer timer) {
		System.out.println("| Scheduler 1 running");
		
	}

	@Override
	public String getJobName() {
		return "Run test scheduled job";
	}

	@Override
	public void run(Timer timer) throws Exception {
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public void runScheduler() {
//		System.out.println("|                                           Scheduler 1 running");
//		
//	}
}
