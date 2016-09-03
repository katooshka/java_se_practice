package collections.guava.transforms;

import java.util.List;

public interface ThreadPreviewDao {
    List<ThreadPreview> getThreadsPreviews(int boardID);
}
