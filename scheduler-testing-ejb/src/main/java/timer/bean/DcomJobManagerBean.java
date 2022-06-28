package timer.bean;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import timer.model.DcomTimerEntity;

@Stateless
public class DcomJobManagerBean {

	
	@PersistenceContext
	EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateLastExecuted(String jobName, Calendar lastExecution, Calendar nextExecution, String lastResult) {
		
		DcomTimerEntity dcomTimerEntity = entityManager.find(DcomTimerEntity.class, jobName);
		
		if (dcomTimerEntity == null) {
			dcomTimerEntity = new DcomTimerEntity();
			dcomTimerEntity.setJobName(jobName);
			dcomTimerEntity.setLastExecuted(lastExecution);
			dcomTimerEntity.setNextExecution(nextExecution);
			dcomTimerEntity.setLastResult(lastResult);
			entityManager.persist(dcomTimerEntity);
		} else {
			
			// push to the database
			entityManager.flush();
		}
		
	}

		
}
