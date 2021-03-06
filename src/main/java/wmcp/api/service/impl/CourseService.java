package wmcp.api.service.impl;

import wmcp.api.model.Course;
import wmcp.api.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements service workflow methods for Course entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 11/20/2016
 */
@Service
public class CourseService extends CrudService<Course> {

    @Autowired
    public CourseService(ICrudRepository<Course> repository) {
        super(repository);
    }
}
