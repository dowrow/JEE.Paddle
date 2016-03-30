package api;

import business.api.Uris;
import business.wrapper.TokenWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.entities.Role;

public class RestService {

    public static final String URL = "http://localhost:8080/JEE.Paddle.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }

    public String registerAndLoginPlayer() {
        UserWrapper player = new UserWrapperBuilder().build();
        new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(player.getUsername(), player.getPassword())
                .clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }

    public void createCourt(String id) {
        new RestBuilder<Object>(URL).path(Uris.COURTS).param("id", id).basicAuth(this.loginAdmin(), "").post().build();
    }

	public String registerAndLoginTrainer() {
		 UserWrapper player = new UserWrapperBuilder().role(Role.TRAINER).build();
	     new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
	     TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(player.getUsername(), player.getPassword())
	    		 .clazz(TokenWrapper.class).post().build();
	     return token.getToken();
	}

}
