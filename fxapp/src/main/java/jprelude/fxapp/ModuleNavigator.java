package jprelude.fxapp;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

public class ModuleNavigator extends TabPane {
    public ModuleNavigator() {
        this.getStyleClass().add("fxapp-module-navigator");
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        final VBox vbox1 = new VBox(
                new Button("Products", fontAwesome.create(FontAwesome.Glyph.COG)),
                new Button("Variants", fontAwesome.create(FontAwesome.Glyph.COGS)),
                new Button("Categories", fontAwesome.create(FontAwesome.Glyph.INBOX))
        );


        vbox1.getStyleClass().add("fxapp-module-navigator-division");
        TitledPane t1 = new TitledPane("Main data", vbox1);


         final VBox vbox2 = new VBox(
                new Button("Users", fontAwesome.create(FontAwesome.Glyph.USER)),
                new Button("Groups", fontAwesome.create(FontAwesome.Glyph.USERS)),
                new Button("Permission rules", fontAwesome.create(FontAwesome.Glyph.EXCLAMATION_TRIANGLE))
        );

        vbox2.getStyleClass().add("fxapp-module-navigator-division");

        TitledPane t2 = new TitledPane("User management", vbox2);


        final VBox vbox3 = new VBox(
                new Button("Dashboard", fontAwesome.create(FontAwesome.Glyph.DASHBOARD)),
                new Button("Charts", fontAwesome.create(FontAwesome.Glyph.AREA_CHART))
        );

        vbox3.getStyleClass().add("fxapp-module-navigator-division");

        TitledPane t3 = new TitledPane("Statistics", vbox3);

        VBox accordion = new VBox();
        accordion.getStyleClass().add("fxapp-module-navigator-divisions-pane");
        accordion.getChildren().addAll(t1, t2, t3);
        this.setSide(Side.LEFT);

        final Tab tab1 = new Tab("Shop", accordion);
        tab1.setClosable(false);


        final Tab tab2 = new Tab("Media", null);
        tab2.setClosable(false);

        final Tab tab3 = new Tab("Administration", null);
        tab3.setClosable(false);

        this.getTabs().addAll(tab2, tab1, tab3);
    }
}
