import constant.TrelloConstant;
import dto.Board;
import dto.CreateBoard;
import dto.GetBoard;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.TrelloServiceObj;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
public class TestsCrud {

    @Test
    public static void testCreatingNewBoard() {
        Board board = TrelloServiceObj.createBoard();
        String boardName = board.getName();
        assertThat(boardName, containsString(board.getName()));
        String boardId = board.getId();
        TrelloServiceObj.deleteBoard(boardId);
    }

    @Test
    public static void testGettingBoard(){
        Board board = TrelloServiceObj.createBoard();
        String boardId = board.getId();
        String boardName = board.getName();
        Board gettingBoard = TrelloServiceObj.getBoard(boardId);
        Assert.assertTrue(gettingBoard.getName().contains(boardName));
        Assert.assertTrue(gettingBoard.getId().contains(boardId));
        TrelloServiceObj.deleteBoard(boardId);
    }

    @Test
    public static void testUpdatingBoard(){
        Board board = TrelloServiceObj.createBoard();
        board = TrelloServiceObj.boardApiBuilder()
                                .setName("hello")
                                .updateBoard(board.getId());
        String boardName = board.getName();
        assertThat(boardName, containsString("hello"));
        TrelloServiceObj.deleteBoard(board.getId());
    }

    @Test
    public static void testDeletingBoard(){
        Board board = TrelloServiceObj.createBoard();
        String boardId = board.getId();
        TrelloServiceObj.deleteBoard(boardId);
        Board boardExist = TrelloServiceObj.getBoard(boardId);
        assertThat(boardExist, nullValue());
    }
}
