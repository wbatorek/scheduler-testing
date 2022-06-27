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
import timer.bean.Schedule1;

@WebServlet("/timers-reschedule")
public class TimerServletRescheduleTimers extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1110288109364969838L;
	@EJB
	Schedule1 timerScheduler1;
	@EJB
	QueueSchedulerTimer queueSchedulerTimer;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		String message1 = timerScheduler1.recreateTimer();
		String message2 = queueSchedulerTimer.recreateTimer();

		writer.println(message1 + "\n" + message2 );
	}

}
