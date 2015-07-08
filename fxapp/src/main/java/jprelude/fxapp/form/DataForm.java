package jprelude.fxapp.form;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class DataForm extends BorderPane {
    private String title;
    private String subtitle;

    private DataForm(final Builder builder) {
        Objects.requireNonNull(builder);

        this.getStyleClass().add("fxapp-data-form");

        final Label titleLabel = new Label(builder.title);
        titleLabel.getStyleClass().add("fxapp-data-form-title");

        final Label subtitleLabel = new Label(builder.subtitle);
        subtitleLabel.getStyleClass().add("fxapp-data-form-subtitle");

        final HBox headerBox = new HBox();
        headerBox.getStyleClass().add("fxapp-data-form-header");

        headerBox.getChildren().addAll(titleLabel, subtitleLabel);
        this.setTop(headerBox);

        final VBox sectionsBox = new VBox();

        for (final Section section : builder.sections) {
            final Node node = section.createNode();

            sectionsBox.getChildren().add(node);
        }

        this.setCenter(sectionsBox);
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String subtitle;
        private List<ActionVariant> actions;
        private List<Section> sections;

        private Builder() {

        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder subtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder actions(final ActionVariant... actions) {
            this.actions = Arrays.stream(actions)
                    .filter(action -> action != null)
                    .collect(Collectors.toList());

            return this;
        }

        public Builder sections(final Section... sections) {
            this.sections = Arrays.stream(sections)
                    .filter(section -> section != null)
                    .collect(Collectors.toList());

            return this;
        }

        public DataForm build() {
            return new DataForm(this);
        }
    }
}
