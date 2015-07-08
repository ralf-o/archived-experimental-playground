package jprelude.fxapp.form;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import jprelude.fxapp.form.field.FormField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FieldSection extends Section {
    final List<FormField> fields;

    private FieldSection(final Builder builder) {
        assert builder != null;

        this.fields = Collections.unmodifiableList(new ArrayList<>(builder.fields));
    }

    @Override
    public Node createNode() {
        final GridPane ret = new GridPane();
        ret.setPadding(new Insets(20, 20, 20, 20));
        ret.setHgap(10);
        ret.setVgap(5);

        for (int i = 0; i < this.fields.size(); ++i) {
            final FormField field = this.fields.get(i);
            final String caption = field.getCaption();

            ret.add(new Label(field.getCaption()), 0, i);
            ret.add(field.createNode(), 1, i);
        }

        return ret;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<FormField> fields;

        private Builder() {
            this.fields = new ArrayList<>();
        }

        public FieldSection build() {
            return new FieldSection(this);
        }

        public Builder fields(final FormField... fields) {
            this.fields.clear();
            this.fields.addAll(
                    Arrays.stream(fields)
                            .filter(field -> field != null)
                            .collect(Collectors.toList()));

            return this;
        }
    }
}
