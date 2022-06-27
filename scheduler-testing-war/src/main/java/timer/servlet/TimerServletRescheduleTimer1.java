package timer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timer.bean.Schedule1;

@WebServlet("/timer1-reschedule")
public class TimerServletRescheduleTimer1 extends HttpServlet {
	
	@EJB
	Schedule1 timerScheduler1;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4069369375579222591L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 PrintWriter writer = resp.getWriter();

	        String message = timerScheduler1.recreateTimer();

	        writer.println(message);
	}

}
