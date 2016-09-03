package autovalue;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.sun.istack.internal.Nullable;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.*;

@AutoValue
abstract class Star {
    abstract String name();
    @Nullable abstract String historicalName();
    abstract int absoluteMagnitude();
    abstract String spectralClass();
    abstract int temperature();

    public static Builder builder() {
        return new AutoValue_Star.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder historicalName(String name);
        public abstract Builder absoluteMagnitude(int absoluteMagnitude);
        public abstract Builder spectralClass(String spectralClass);
        public abstract Builder temperature(int temperature);

        abstract Star autoBuild();

        public Star build() {
            Star star = autoBuild();
            checkArgument(ImmutableSet.of("O", "B", "A", "F","G", "K","M").contains(star.spectralClass()));
            return star;
        }
    }
}
