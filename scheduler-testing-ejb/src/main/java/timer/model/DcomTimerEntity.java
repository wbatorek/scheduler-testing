package timer.model;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DCOM_JOB")
public class DcomTimerEntity {

	@Id
	@Column(name="JOB_NAME")
	private String jobName;

//	@Column(name="JOB_DESCRIPTION")
//	private String jobDescription;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastExecuted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar nextExecution;

	private String lastResult;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

//	public String getJobDescription() {
//		return jobDescription;
//	}
//
//	public void setJobDescription(String jobDescription) {
//		this.jobDescription = jobDescription;
//	}

	public Calendar getLastExecuted() {
		return lastExecuted;
	}

	public void setLastExecuted(Calendar lastExecuted) {
		this.lastExecuted = lastExecuted;
	}

	public Calendar getNextExecution() {
		return nextExecution;
	}

	public void setNextExecution(Calendar nextExecution) {
		this.nextExecution = nextExecution;
	}

	public String getLastResult() {
		return lastResult;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	
}
