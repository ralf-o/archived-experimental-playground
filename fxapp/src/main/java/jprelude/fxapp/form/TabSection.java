package jprelude.fxapp.form;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TabSection extends Section {
    private final List<TabPage> pages;

    private TabSection(final Builder builder) {
         assert builder != null;

         this.pages = Collections.unmodifiableList(builder.pages);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Node createNode() {
        final TabPane ret = new TabPane();

        ret.setPadding(new Insets(20, 20, 20, 20));

        for (final TabPage page : this.pages) {
            final Tab tab = new Tab();
            tab.setClosable(false);
            tab.setText(page.getTitle());
            ret.getTabs().add(tab);

            final VBox sectionsBox = new VBox();

            for (final Section section : page.getSections()) {
                final Node node = section.createNode();
                sectionsBox.getChildren().add(node);
            }

            tab.setContent(sectionsBox);
        }

        return ret;
    }

    public static class Builder {
        private final List<TabPage> pages = new ArrayList<>();

        private Builder() {
        }

        public TabSection build() {
            return new TabSection(this);
        }

        public Builder pages(final TabPage... pages) {
            this.pages.clear();
            this.pages.addAll(Arrays.asList(pages));
            return this;
        }
    }
}
