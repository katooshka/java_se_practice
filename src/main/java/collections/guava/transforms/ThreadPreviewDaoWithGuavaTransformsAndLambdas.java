package collections.guava.transforms;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.compare;

public class ThreadPreviewDaoWithGuavaTransformsAndLambdas implements ThreadPreviewDao {
    private static final Comparator<List<Post>> LAST_POST_ID_COMPARATOR =
            (t1, t2) -> compare(
                    t1.get(t1.size() - 1).getId(),
                    t2.get(t2.size() - 1).getId());

    private final PostsDao postsDao;

    public ThreadPreviewDaoWithGuavaTransformsAndLambdas(PostsDao postsDao) {
        this.postsDao = postsDao;
    }

    public List<ThreadPreview> getThreadsPreviews(int boardID) {
        return Lists.transform(
                Ordering.from(LAST_POST_ID_COMPARATOR).reverse()
                        .sortedCopy(Multimaps.asMap(
                                Multimaps.index(
                                        postsDao.selectPostsByBoard(boardID),
                                        Post::getThreadId))
                                .values()),
                ThreadPreview::createFromFullThread);
    }
}
