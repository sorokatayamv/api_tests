package service;

import static io.restassured.http.ContentType.URLENC;
import static java.util.Locale.ENGLISH;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constant.TrelloConstant;
import dto.Board;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.net.URI;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.http.HttpStatus;

public class TrelloServiceObj {

    public static URI trelloUri;
    private static ResourceBundle resourceBundle;
    private HashMap<String, String> params = new HashMap<>();

    static {
        resourceBundle = ResourceBundle.getBundle("trello", ENGLISH);
        trelloUri = URI.create(resourceBundle.getString("baseurl"));
    }

    private TrelloServiceObj() {
    }

    public static class BoardApiBuilder {

        private TrelloServiceObj trelloServiceObj;

        private BoardApiBuilder(TrelloServiceObj trelloServiceObj) {
            this.trelloServiceObj = trelloServiceObj;
        }

        public BoardApiBuilder setName(String name) {
            trelloServiceObj.params.put(
                TrelloConstant.NAME.getConstantName(),
                name);
            return this;
        }

        public Board createBoard() {
            return formBoardFromResponse(
                RestAssured
                    .given()
                    .spec(requestConfiguration())
                    .params(trelloServiceObj.params)
                    .log().all()
                    .post(resourceBundle.getString("baseurl")
                        + resourceBundle.getString("boards")
                    ).prettyPeek()
            );
        }

        public int deleteBoard(String id) {
            return RestAssured
                .given()
                .spec(requestConfiguration())
                .log().all()
                .delete(resourceBundle.getString("baseurl")
                    + resourceBundle.getString("boards")
                    + id
                ).prettyPeek()
                .then()
                .extract()
                .statusCode();
        }

        public Board updateBoard(String id) {
            return formBoardFromResponse(
                RestAssured
                    .given()
                    .spec(requestConfiguration())
                    .params(trelloServiceObj.params)
                    .log().all()
                    .put(resourceBundle.getString("baseurl")
                        + resourceBundle.getString("boards")
                        + id
                    ).prettyPeek()
            );
        }

        public Board getBoard(String id) {
            return formBoardFromResponse(
                RestAssured
                    .given()
                    .spec(requestConfiguration())
                    .params(trelloServiceObj.params)
                    .log().all()
                    .put(resourceBundle.getString("baseurl")
                        + resourceBundle.getString("boards")
                        + id
                    ).prettyPeek()
            );
        }
    }

    public static BoardApiBuilder boardApiBuilder() {
        return new BoardApiBuilder(new TrelloServiceObj());
    }

    public static Board createBoard() {
        TrelloServiceObj api = new TrelloServiceObj();
        String boardName = "Board " + new Random().nextInt(1000);
        api.params.put(TrelloConstant.NAME.getConstantName(), boardName);
        return new BoardApiBuilder(api).createBoard();
    }

    public static int deleteBoard(final String id) {
        TrelloServiceObj api = new TrelloServiceObj();
        return new BoardApiBuilder(api).deleteBoard(id);
    }

    public static Board getBoard(String id) {
        TrelloServiceObj api = new TrelloServiceObj();
        return new BoardApiBuilder(api).getBoard(id);
    }

    private static Board formBoardFromResponse(Response response) {
        if (HttpStatus.SC_OK == response.then().extract().statusCode()) {
            return new Gson().
                                 fromJson(response.asString().trim(),
                                     new TypeToken<Board>() {
                                     }.getType());
        } else {
            return null;
        }
    }

    private static RequestSpecification requestConfiguration() {
        return new RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .setBaseUri(trelloUri)
            .addParam("key", resourceBundle.getString("key"))
            .addParam("token", resourceBundle.getString("token"))
            .build().contentType(URLENC.withCharset("utf-8"));
    }
}
