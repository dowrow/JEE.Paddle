package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.TrainingCreationWrapper;
import business.wrapper.TrainingWrapper;

public class TrainingResourceFunctionalTesting {
	
	private RestService restService = new RestService();
	private int courtId = 10;
	private String token;
	
	@Before
	public void before() {
		restService.deleteAll();
		restService.createCourt(courtId + "");
		token = restService.registerAndLoginTrainer();
	}
	
	@Test
	public void testCreateTraining() {
	
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);

		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
		LogManager.getLogger(this.getClass()).info(
				"testCreateTraining (response):\n    " + response);
		
		try {
			wrapper.setStartDate(aMonthLater);
			wrapper.setEndDate(tomorrow);
			new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.BAD_REQUEST);
			LogManager.getLogger(this.getClass()).info(
					"testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
		try {
			wrapper.setCourtId(-1);
			new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.NOT_FOUND);
			LogManager.getLogger(this.getClass()).info(
					"testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}
	
	
	@Test
	public void testDeleteTraining() {
	
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);

		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
	    TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
	    new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId()).basicAuth(token, "").delete().build();	
		
		try {
			new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/999999").basicAuth(token, "").delete().build();	
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.NOT_FOUND);
			LogManager.getLogger(this.getClass()).info(
					"testDeleteTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
		
	}
	
	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}

}
