package api;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

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
		new RestBuilder<>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").body(wrapper).post().build();

		try {
			wrapper.setStartDate(aMonthLater);
			wrapper.setEndDate(tomorrow);
			new RestBuilder<>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").body(wrapper).post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.BAD_REQUEST);
			LogManager.getLogger(this.getClass()).info(
					"testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
		try {
			wrapper.setCourtId(-1);
			new RestBuilder<>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").body(wrapper).post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.NOT_FOUND);
			LogManager.getLogger(this.getClass()).info(
					"testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}

	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}

}
