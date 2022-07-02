package timer.bean;

import java.util.Calendar;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

public abstract class AbstractDcomTimer implements TimedObject {

	@Inject
	private DcomJobManagerBean dcomJobManagerBean;

	@Resource
	private TimerService timerService;
	
	public abstract String getJobName();

	public abstract String getTimerName();

	public abstract long getTimerInterval();

	public abstract boolean isPersistent();

	@PostConstruct
	public void init() {
		System.out.println("Initializing timer " + getTimerName() + " with interval (sec) " + getTimerInterval());

		rescheduleTimer();
	}

	public Timer getTimer() {
		for (Timer timer : timerService.getAllTimers()) {
			if (timer.getInfo() != null) {
				if (timer.getInfo().toString().equals(getTimerName())) {
					return timer;
				}
			}
		}
		return null;
	}

	public String rescheduleTimer() {
		for (Timer timer : timerService.getAllTimers()) {
			if (timer.getInfo() != null) {
				if (timer.getInfo().toString().equals(getTimerName())) {
					timer.cancel();
				}
			}
		}
		createTimer(getTimerInterval(), isPersistent(), getTimerName());
		System.out.println("Timer [" + getTimerName() + "] has been recreated during startup");
		return "Timer [" + getTimerName() + "] has been recreated during startup";

	}

	public String recreateTimer() {
		for (Timer timer : timerService.getAllTimers()) {
			if (timer.getInfo() != null) {
				if (timer.getInfo().toString().equals(getTimerName())) {
					timer.cancel();
				}
			}
		}
		createTimer(getTimerInterval(), isPersistent(), getTimerName());
		System.out.println("Timer [" + getTimerName() + "] has been recreated using servlet");
//		logger.debug("Timer [" + getTimerName() + "] has been recreated using servlet");
		return "Timer [" + getTimerName() + "] has been recreated using servlet";

	}

	public Timer createTimer(long interval, boolean isPersistent, String info) {
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(isPersistent);
		timerConfig.setInfo(info);
		return timerService.createIntervalTimer(0, interval, timerConfig);
	}

	public String getTimerInfo() {
		System.out.println("getTimerInfo has been called");
		Collection<Timer> timersCollection = timerService.getAllTimers();
		String message = null;
		for (Timer timer : timersCollection) {
			if (timer.getInfo() != null) {
				if (timer.getInfo().toString().equals(getTimerName())) {
					String timerId = timer.toString();
					String timerInfo = " | Timer info: " + timer.getInfo().toString();
					String isPersistent = " | isPersistent=" + timer.isPersistent();
					message = message + timerId + timerInfo + isPersistent + "\n";
				}
			}
		}
		if (message == null) {
			System.out.println("No existing instances of " + getTimerName());
		}
		return message;

	}

	public String getTimersInfo() {
		System.out.println("getTimersInfo has been called");
		Collection<Timer> timersCollection = timerService.getAllTimers();
		String message = "";
		for (Timer timer : timersCollection) {
			String timerId = timer.toString();
			String timerInfo;
			if (timer.getInfo() == null) {
				timerInfo = " | Timer info: null";
			} else {
				timerInfo = " | Timer info: " + timer.getInfo().toString();
			}
			String isPersistent = " | isPersistent=" + timer.isPersistent();
			message = message + timerId + timerInfo + isPersistent + "\n";
		}
		return message;
	}

	public String cancellAllTimers() {
		for (Timer timer : timerService.getAllTimers()) {
			timer.cancel();
		}
		System.out.println("All timers cancelled");
//		logger.debug("All timers cancelled");
		return "All timers cancelled";
	}

	public String cancellTimer() {

		for (Timer timer : timerService.getAllTimers()) {
			if (timer.getInfo() != null) {
				if (timer.getInfo().toString().equals(getTimerName())) {
					timer.cancel();
					System.out.println("Timer " + getTimerName() + " cancelled");
					return "Timer " + getTimerName() + " cancelled";
				}
			}
		}
		System.out.println("Unable to cancel Timer " + getTimerName() + " because it is not running");
		return "Unable to cancel Timer " + getTimerName() + " because it is not running";
	}

	public abstract void run(Timer timer) throws Exception;

	@Override
	public void ejbTimeout(Timer timer) {
		
		try {
			run(timer);
			dcomJobManagerBean.updateLastExecuted(getJobName(), Calendar.getInstance(), Calendar.getInstance(), "no new messeges");

		} catch (Exception e) {
			System.out.println("Exception during " + e.getMessage());
			
		}
	}

}
