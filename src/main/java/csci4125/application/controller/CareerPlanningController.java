package csci4125.application.controller;

import csci4125.application.model.Course;
import csci4125.application.model.Job;
import csci4125.application.service.ICareerPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Defines the REST API for career planning workflows.
 *
 * @author tlmader.dev@gmail.com
 * @since 11/22/2016
 */

@RestController
@RequestMapping("api/careerplanning")
public class CareerPlanningController {

    @Autowired
    ICareerPlanningService service;

    @RequestMapping(value = {"/jobs/{primarySector}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findJobsByPrimarySector(@PathVariable("primarySector") String primarySector) {
        return new ResponseEntity<>(this.service.findJobsByPrimarySector(primarySector), HttpStatus.OK);
    }

    @RequestMapping(value = {"/courses/{personId}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Course>> findCourseSetForJobProfile(@PathVariable("jobProfileId") String jobProfileId, @RequestParam("personId") String personId) {
        return new ResponseEntity<>(this.service.findCourseSetForJobProfile(jobProfileId, personId), HttpStatus.OK);
    }
}
