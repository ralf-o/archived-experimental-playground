package jprelude.fxapp.form;

import java.util.Objects;

public final class ActionGroup extends ActionVariant {
    private ActionGroup(final Builder builder) {
        Objects.requireNonNull(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Action[] actions;

        private Builder() {
        }

        public Builder actions(final ActionVariant... actions) {
            return this;
        }

        public ActionGroup build() {
            return new ActionGroup(this);
        }
    }
}
