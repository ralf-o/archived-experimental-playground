package jprelude.fxapp.form;

import java.util.*;
import java.util.stream.Collectors;

public final class TabPage {
    private final String title;
    private List<Section> sections;

    private TabPage(final Builder builder) {
        Objects.requireNonNull(builder);

        this.title = builder.title;
        this.sections = Collections.unmodifiableList(builder.sections);
    }

    public String getTitle() {
        return this.title;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private final List<Section> sections;

        private Builder() {
            this.sections = new ArrayList<>();
        }

        public TabPage build() {
            return new TabPage(this);
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder sections(final Section... sections) {
            this.sections.clear();

            this.sections.addAll(Arrays.stream(sections)
                    .filter(section -> section != null)
                    .collect(Collectors.toList()));

            return this;
        }
    }
}
