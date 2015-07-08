package jprelude.fxapp;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jprelude.fxapp.form.*;
import jprelude.fxapp.form.field.TextField;
import org.controlsfx.glyphfont.Glyph;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(ClassLoader.getSystemResource("fonts/fontawesome-webfont.ttf").toExternalForm(), 14);
        final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(this.getClass().getResourceAsStream("/fonts/fontawesome-webfont.ttf"));
        final BorderPane rootPane = new BorderPane();


        rootPane.setMinHeight(screenBounds.getHeight() * 0.8);
        rootPane.setMinWidth(screenBounds.getWidth() * 0.8);

        final BorderPane headerPane = new BorderPane();

        final HBox headerCenterBox = new HBox();
        headerCenterBox.getStyleClass().add("fxapp-application-header");
        rootPane.setTop(headerPane);

        final HBox footerBox = new HBox();
        footerBox.getStyleClass().add("fxapp-application-footer");
        footerBox.getChildren().add(new Label("Status..."));
        rootPane.setBottom(footerBox);

        final Label appTitleLabel = new Label("My awesome app");
        appTitleLabel.getStyleClass().add("fxapp-application-header-title");

        headerCenterBox.getChildren().add(appTitleLabel);
        final Label appSubtitleLabel = new Label("bla bla bla");

        appSubtitleLabel.getStyleClass().add("fxapp-application-header-subtitle");
        headerCenterBox.getChildren().add(appSubtitleLabel);

        headerPane.setCenter(headerCenterBox);

        headerPane.setRight(new Button("", new Glyph("FontAwesome", "BARS")));

        final ModuleNavigator moduleNavigator = new ModuleNavigator();
        rootPane.setLeft(moduleNavigator);

        final ModulePane modulePane = new ModulePane();
        rootPane.setCenter(modulePane);


        final DataForm dataForm = DataForm.builder()
            .title("Product")
            .subtitle("blablabla")
            .actions(
                    Action.builder()
                            .caption("Action1")
                            .build(),
                    Action.builder()
                            .caption("Action2")
                            .build(),
                    ActionGroup.builder()
                            .actions(
                                    Action.builder()
                                            .caption("A")
                                            .build(),
                                    Action.builder()
                                            .caption("B")
                                            .build(),
                                    Action.builder()
                                            .caption("C")
                                            .build())
                            .build())
            .sections(
                    TabSection.builder()
                            .pages(
                                    TabPage.builder()
                                            .title("First page")
                                            .sections(
                                                FieldSection.builder()
                                                .fields(
                                                    TextField.builder()
                                                        .caption("First name")
                                                        .build(),
                                                    TextField.builder()
                                                        .caption("Last name")
                                                        .build()
                                                )
                                                .build()
                                            )
                                            .build(),
                                    TabPage.builder()
                                            .title("Second page")
                                            .sections(
                                                    TabSection.builder()
                                                            .pages(
                                                                    TabPage.builder()
                                                                            .title("Sub page A")
                                                                            .build())
                                                            .build())
                                            .build())
                            .build())
            .build();

        modulePane.getChildren().add(dataForm);

        //AquaFx.style();
        Scene scene = new Scene(rootPane);

        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
