package collections.guava.transforms;

import com.google.auto.value.AutoValue;
import org.joda.time.Instant;

@AutoValue
public abstract class Post {
    public abstract int getId();
    public abstract int getThreadId();
    public abstract Instant getPostTime();
    public abstract String getAuthor();
    public abstract String getMessage();

    public static Builder builder(){
        return new AutoValue_Post.Builder()
                .setId(-1)
                .setThreadId(-1);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId (int id);
        public abstract Builder setThreadId (int threadId);
        public abstract Builder setPostTime(Instant time);
        public abstract Builder setAuthor(String author);
        public abstract Builder setMessage(String message);
        public abstract Post build();
    }
}
