package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        String welcomeText = "Hello! I am Duke.\n"
                            + "How may I assist you?\n";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeText, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.reply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    String logo = "             " + " ____        _\n"
            + "             " + "|  _ \\ _   _| | _____\n"
            + " Hello! I am " + "| | | | | | | |/ / _ \\\n"
            + "             " + "| |_| | |_| |   <  __/\n"
            + "             " + "|____/ \\__,_|_|\\_\\___|\n";

}
