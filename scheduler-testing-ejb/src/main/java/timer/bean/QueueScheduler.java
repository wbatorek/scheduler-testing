package timer.bean;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class QueueScheduler {
	
	@Schedule(second = "*/1", minute = "*", hour = "*")
	public void checkForNewMessagesAutomaticTimer() {
		System.out.println("Automatic Timer running");
	}
	
	public void checkForNewMessagesProgramaticTimer() {
		System.out.println("Programatic Timer running");
	}
	

}
