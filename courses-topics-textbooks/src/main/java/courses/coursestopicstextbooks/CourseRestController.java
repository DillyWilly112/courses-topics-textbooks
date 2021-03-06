package courses.coursestopicstextbooks;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

	
	@Resource
	private CourseRepository courseRepo;
	
	@Resource
	private TopicRepository topicRepo;
	
	@RequestMapping("")
	public Iterable<Course> findAllCourses() {
		return courseRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Optional<Course> findOneCourse(@PathVariable long id) {
		return courseRepo.findById(id);
	}
	
	@RequestMapping("/topics/{topicName}")
	public Collection<Course> findAllCoursesByTopic(@PathVariable(value="topicName") String topicName) {
		Topic topic = topicRepo.findByNameIgnoreCaseLike(topicName);
		return courseRepo.findByTopicsContains(topic);
	}
}
