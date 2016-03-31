package api;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.TokenWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.entities.Role;

public class RestService {

	public static final String URL = "http://localhost:8080/JEE.Paddle.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

	public void deleteAll() {
		new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete()
				.build();
	}

	public String loginAdmin() {
		TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin")
				.clazz(TokenWrapper.class).post().build();
		return token.getToken();
	}
	
	public void logOut(String username) {
		
	}

	public String registerAndLoginPlayer() {
		UserWrapper player = new UserWrapperBuilder().build();
		new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
		TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS)
				.basicAuth(player.getUsername(), player.getPassword()).clazz(TokenWrapper.class).post().build();
		return token.getToken();
	}

	public void createCourt(String id) {
		new RestBuilder<Object>(URL).path(Uris.COURTS).param("id", id).basicAuth(this.loginAdmin(), "").post().build();
	}

	public String registerAndLoginTrainer() {
		return registerAndLoginTrainer("trainer0", "password0");
	}
	
	public String registerAndLoginTrainer(String username, String password) {
		UserWrapper player = new UserWrapperBuilder().username(username).password(password).role(Role.TRAINER).build();
		try {
			new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
		} catch (HttpClientErrorException httpError) {
			LogManager.getLogger(this.getClass()).info("registerAndLoginTrainer (" + httpError.getMessage() + "):\n    "
					+ httpError.getResponseBodyAsString());
		}
		TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS)
				.basicAuth(player.getUsername(), player.getPassword()).clazz(TokenWrapper.class).post().build();
		return token.getToken();
	}

}
