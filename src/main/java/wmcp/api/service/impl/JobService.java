package wmcp.api.service.impl;

import wmcp.api.model.Job;
import wmcp.api.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements service workflow methods for Job entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 11/20/2016
 */
@Service
public class JobService extends CrudService<Job> {

    @Autowired
    public JobService(ICrudRepository<Job> repository) {
        super(repository);
    }
}
