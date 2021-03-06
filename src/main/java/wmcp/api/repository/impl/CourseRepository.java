package wmcp.api.repository.impl;

import wmcp.api.model.Course;
import org.springframework.stereotype.Component;

/**
 * Implements data access methods for Course entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 2016-11-17
 */
@Component
public class CourseRepository extends CrudRepository<Course> {

    public CourseRepository() {
        super((x, y) -> {
            x.setTitle(y.getTitle());
            x.setLevel(y.getLevel());
            x.setDescription(y.getDescription());
            x.setStatus(y.getStatus());
            x.setPrice(y.getPrice());
        }, Course.class);
    }
}
