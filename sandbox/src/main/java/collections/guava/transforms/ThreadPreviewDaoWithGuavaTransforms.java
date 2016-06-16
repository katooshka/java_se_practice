package collections.guava.transforms;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

import java.util.List;

public class ThreadPreviewDaoWithGuavaTransforms {
    private static final Ordering<List<Post>> LAST_POST_ID_ORDER = new Ordering<List<Post>>() {
        @Override
        public int compare(List<Post> thread1, List<Post> thread2) {
            return Integer.compare(
                    thread1.get(thread1.size() - 1).getId(),
                    thread2.get(thread2.size() - 1).getId());
        }
    };

    private static final Function<List<Post>, ThreadPreview> POST_TO_THREAD_PREVIEWS_FUNCTION =
            new Function<List<Post>, ThreadPreview>() {
        @Override
        public ThreadPreview apply(List<Post> input) {
            return ThreadPreview.createFromFullThread(input);
        }
    };

    private static final Function<Post, Integer> POST_TO_THREAD_ID_FUNCTION = new Function<Post, Integer>() {
        @Override
        public Integer apply(Post input) {
            return input.getThreadId();
        }
    };

    private final PostsDao postsDao;

    public ThreadPreviewDaoWithGuavaTransforms(PostsDao postsDao) {
        this.postsDao = postsDao;
    }

    public List<ThreadPreview> getThreadsPreviews(int boardID) {
        return Lists.transform(
                LAST_POST_ID_ORDER.reverse()
                        .sortedCopy(
                                Multimaps.asMap(
                                        Multimaps.index(
                                                postsDao.selectPostsByBoard(boardID),
                                                POST_TO_THREAD_ID_FUNCTION))
                                        .values()),
                POST_TO_THREAD_PREVIEWS_FUNCTION);
    }
}
