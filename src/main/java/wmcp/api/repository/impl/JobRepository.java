package wmcp.api.repository.impl;

import wmcp.api.model.Job;
import org.springframework.stereotype.Component;

/**
 * Implements data access methods for Job entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 2016-11-17
 */
@Component
public class JobRepository extends CrudRepository<Job> {

    public JobRepository() {
        super((x, y) -> {
            x.setJobProfile(y.getJobProfile());
            x.setCompany(y.getCompany());
            x.setType(y.getType());
            x.setPayRate(y.getPayRate());
            x.setPayType(y.getPayType());
        }, Job.class);
    }
}
