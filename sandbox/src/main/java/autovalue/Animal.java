package autovalue;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Animal {

    abstract String name();

    abstract int legsNumber();

    public static Builder builder() {
        return new AutoValue_Animal.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder legsNumber(int legsNumber);

        public abstract Animal build();
    }
}
