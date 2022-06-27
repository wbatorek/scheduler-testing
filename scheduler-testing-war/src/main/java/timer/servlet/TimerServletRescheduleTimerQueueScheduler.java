package timer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.bean.QueueSchedulerTimer;


@WebServlet("/timer-queue-scheduler-reschedule")
public class TimerServletRescheduleTimerQueueScheduler extends HttpServlet {
	
	@EJB
	QueueSchedulerTimer queueSchedulerTimer;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4069369375579222591L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 PrintWriter writer = resp.getWriter();

	        String message = queueSchedulerTimer.recreateTimer();

	        writer.println(message);
	}

}
