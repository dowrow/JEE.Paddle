package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.TrainingCreationWrapper;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;

public class TrainingResourceFunctionalTesting {
	
	private RestService restService = new RestService();
	private int courtId = 10;
	private String trainerToken;
	
	@Before
	public void before() {
		restService.deleteAll();
		restService.createCourt(courtId + "");
		trainerToken = restService.registerAndLoginTrainer();
	}
	
	@Test
	public void testCreateTraining() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);
		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
		LogManager.getLogger(this.getClass()).info(
				"testCreateTraining (response):\n    " + response);
		try {
			wrapper.setStartDate(aMonthLater);
			wrapper.setEndDate(tomorrow);
			new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.BAD_REQUEST);
			LogManager.getLogger(this.getClass()).info(
					"testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
		try {
			wrapper.setCourtId(-1);
			new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
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
	    TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
	    new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId()).basicAuth(trainerToken, "").delete().build();	
		try {
			new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/999999").basicAuth(trainerToken, "").delete().build();	
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(httpError.getStatusCode(), HttpStatus.NOT_FOUND);
			LogManager.getLogger(this.getClass()).info(
					"testDeleteTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}
	
	@Test
	public void testShowTrainings() {
	    List<TrainingWrapper> list = Arrays.asList(new RestBuilder<TrainingWrapper[]>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper[].class).get().build());
	    LogManager.getLogger(this.getClass()).info(
				"testShowTrainings:\n    " + list);
	    assertTrue(list.isEmpty());
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);
		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
		
		list = Arrays.asList(new RestBuilder<TrainingWrapper[]>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper[].class).get().build());
	    LogManager.getLogger(this.getClass()).info(
				"testShowTrainings:\n    " + list);
		assertEquals(list.size(), 1);
	}
	

	@Test
	public void testAddPupilToTraining() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);
		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();
		new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId() + Uris.PUPILS).basicAuth(trainerToken, "").post().build();
	}

	@Test
	public void testDeletePupilFromTraining() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Calendar aMonthLater = (Calendar) tomorrow.clone();
		aMonthLater.add(Calendar.MONTH, 1);
		TrainingCreationWrapper wrapper = new TrainingCreationWrapper(courtId, tomorrow, aMonthLater);
		// create training
		TrainingWrapper response = new RestBuilder<TrainingWrapper>(RestService.URL).path(Uris.TRAININGS).basicAuth(trainerToken, "").clazz(TrainingWrapper.class).body(wrapper).post().build();		
		// add a pupil
		new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId() + Uris.PUPILS).basicAuth(trainerToken, "").post().build();
		// get its id
		List<UserWrapper> pupils = Arrays.asList(new RestBuilder<UserWrapper[]>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId() + Uris.PUPILS).basicAuth(trainerToken, "").clazz(UserWrapper[].class).get().build());
	    LogManager.getLogger(this.getClass()).info(
				"testDeletePupilFromTraining (pupils):\n    " + pupils);
		// delete pupil
		new RestBuilder<>(RestService.URL).path(Uris.TRAININGS + "/" + response.getId() + Uris.PUPILS + "/" + pupils.get(0).getId()).basicAuth(trainerToken, "").delete().build();

	}
	
	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}

}
