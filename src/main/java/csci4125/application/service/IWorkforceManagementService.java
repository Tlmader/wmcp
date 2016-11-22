package csci4125.application.service;

import csci4125.application.model.Job;
import csci4125.application.model.Person;
import csci4125.application.model.Works;

import java.util.List;

/**
 * Defines methods for handling orchestration of Workforce Management workflows and performing calls to repositories.
 *
 * @author tlmader.dev@gmail.com
 * @since 11/21/2016
 */
public interface IWorkforceManagementService {

    /**
     * Assigns a Job to a Person if qualified.
     *
     * @param personId an ID for the Person to be hired
     * @param jobId    an ID for the Job to be assigned
     * @return the Person to Job association
     */
    Works assignPersonToJob(String personId, String jobId);

    /**
     * Returns the List of possible Jobs for a specific Person by an ID.
     *
     * @param personId an ID for the Person
     * @return the List of Jobs
     */
    List<Job> findJobsForPersonByKnownSkills(String personId);

    /**
     * Returns the List of Persons qualified for a specific Job by an ID.
     *
     * @param jobId an ID for a Job
     * @return the List of Persons
     */
    List<Person> findPersonsForJobByRequiredSkills(String jobId);
}
