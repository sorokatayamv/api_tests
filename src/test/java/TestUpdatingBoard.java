import daraprovider.TestData;
import dto.Board;
import org.testng.annotations.Test;
import service.TrelloServiceObj;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestUpdatingBoard {

    @Test(dataProvider = "boardNames", dataProviderClass = TestData.class)
    public static void testUpdatingBoardWithDifferentNames(String name){
        Board board = TrelloServiceObj.createBoard();
        board = TrelloServiceObj.boardApiBuilder()
                                .setName(name)
                                .updateBoard(board.getId());
        String nameBoard = board.getName();
        assertThat(nameBoard, containsString(name));
        TrelloServiceObj.deleteBoard(board.getId());
    }
}
