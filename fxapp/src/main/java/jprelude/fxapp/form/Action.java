package jprelude.fxapp.form;

public class Action extends ActionVariant {
    private final String caption;

    private Action(final Builder builder) {
        this.caption = builder.caption;
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

        public Action build() {
            return new Action(this);
        }
    }
}
