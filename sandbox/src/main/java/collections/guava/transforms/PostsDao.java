package collections.guava.transforms;

import com.google.common.collect.ImmutableList;

public interface PostsDao {

    ImmutableList<Post> selectPostsByThread(int threadId);

    ImmutableList<Post> selectPostsByBoard(int boardId);

    void insertPost(Post post);
}
