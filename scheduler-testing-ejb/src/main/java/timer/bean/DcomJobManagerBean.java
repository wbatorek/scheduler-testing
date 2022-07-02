package timer.bean;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import timer.model.DcomTimerEntity;

@Stateless
public class DcomJobManagerBean {

	
	@PersistenceContext(unitName = "job-unit")
	EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateLastExecuted(String jobName, Calendar lastExecution, Calendar nextExecution, String lastResult) {
		nextExecution.add(Calendar.SECOND, +1);
		DcomTimerEntity dcomTimerEntity = entityManager.find(DcomTimerEntity.class, jobName);
//			System.out.println(dcomTimerEntity.toString() + " found "+ lastResult);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		if (dcomTimerEntity == null) {
			dcomTimerEntity = new DcomTimerEntity();
			dcomTimerEntity.setJobName(jobName);
			dcomTimerEntity.setLastExecuted(lastExecution);
			dcomTimerEntity.setNextExecution(nextExecution);
			dcomTimerEntity.setLastResult(lastResult);
			System.out.println("Entity for job "+dcomTimerEntity.getJobName() + " created");
			entityManager.persist(dcomTimerEntity);
			System.out.println("PERSISTED");
		} else {
			dcomTimerEntity.setLastExecuted(lastExecution);
			dcomTimerEntity.setNextExecution(nextExecution);
			dcomTimerEntity.setLastResult(lastResult);
			// push to the database
			entityManager.flush();
			System.out.println("db updated");
		}
		
	}

		
}
