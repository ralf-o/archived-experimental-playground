package jprelude.fxapp.form.field;

import javafx.scene.Node;

public final class TextField extends FormField {
    private final String caption;

    private TextField(final Builder builder) {
        assert builder != null;

        this.caption = builder.caption;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }

    @Override
    public Node createNode() {
        final javafx.scene.control.TextField ret = new javafx.scene.control.TextField();
        return ret;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String caption;

        private Builder() {
        }

        public Builder caption(final String caption) {
            this.caption = caption;
            return this;
        }

        public TextField build() {
            return new TextField(this);
        }
    }
}
