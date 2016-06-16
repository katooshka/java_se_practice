package collections.guava.transforms;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ThreadPreview {
    private final Post opPost;
    private final ImmutableList<Post> tailPosts;

    public static ThreadPreview createFromFullThread(List<Post> thread) {
        if (thread.size() > 3) {
            return new ThreadPreview(thread.get(0),
                    thread.subList(thread.size() - 3, thread.size()));
        } else {
            return new ThreadPreview(thread.get(0), thread.subList(1, thread.size()));
        }
    }

    public ThreadPreview(Post opPost, List<Post> tailPosts) {;
        checkArgument(checkNotNull(tailPosts).size() <= 3);
        for (Post tailPost : tailPosts) {
            checkArgument(tailPost.getThreadId() == opPost.getThreadId());
        }
        this.opPost = checkNotNull(opPost);
        this.tailPosts = ImmutableList.copyOf(tailPosts);
    }

    public ImmutableList<Post> getTailPosts() {
        return tailPosts;
    }

    public Post getOpPost() {
        return opPost;
    }

    public int getThreadId() {
        return opPost.getThreadId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadPreview that = (ThreadPreview) o;

        if (opPost != null ? !opPost.equals(that.opPost) : that.opPost != null) return false;
        return tailPosts != null ? tailPosts.equals(that.tailPosts) : that.tailPosts == null;
    }

    @Override
    public int hashCode() {
        int result = opPost != null ? opPost.hashCode() : 0;
        result = 31 * result + (tailPosts != null ? tailPosts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThreadPreview{" +
                "opPost=" + opPost +
                ", tailPosts=" + tailPosts +
                '}';
    }
}
