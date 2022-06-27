package timer.bean;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import timer.model.DcomTimerEntity;

@Stateless
public class DcomJobManagerBean {

//	@Inject
//	EntityManager entityManager;

//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	public void updateLastExecuted(String jobName, Calendar lastExecution, Calendar nextExecution, String lastResult) {
//		
//		DcomTimerEntity dcomTimerEntity = entityManager.find(DcomTimerEntity.class, jobName);
//		
//		if (dcomTimerEntity == null) {
//			dcomTimerEntity = new DcomTimerEntity();
//			dcomTimerEntity.setJobName(jobName);
//			dcomTimerEntity.setLastExecuted(lastExecution);
//			dcomTimerEntity.setNextExecution(nextExecution);
//			entityManager.persist(dcomTimerEntity);
//		} else {
//			
//			// push to the database
//			entityManager.flush();
//		}
//		
//	}

		
}
