package api;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.TrainingCreationWrapper;

public class TrainingResourceFunctionalTesting {

	RestService restService = new RestService();

	@Test
	public void testCreateTraining() {
		int courtId = 10;
		restService.createCourt(courtId + "");
		String token = restService.registerAndLoginTrainer();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);

		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		String response = (String) new RestBuilder<>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "")
				.body(wrapper).post().build();

		LogManager.getLogger(this.getClass()).info("testCreateTraining (" + response + ")");

	}

	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}

}
