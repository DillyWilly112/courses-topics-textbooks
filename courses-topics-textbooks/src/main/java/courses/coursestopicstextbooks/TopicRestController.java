package courses.coursestopicstextbooks;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicRestController {
	@Resource
	private CourseRepository courseRepo;
	
	@Resource
	private TopicRepository topicRepo;
	
	@RequestMapping("")
	public Iterable<Topic> findAllTopics() {
		return topicRepo.findAll();
	}
	
	@RequestMapping("/{topicName}/courses")
	public Collection<Course> findAllCoursesByTopic(@PathVariable(value="topicName") String topicName) {
		Topic topic = topicRepo.findByNameIgnoreCaseLike(topicName);
		return courseRepo.findByTopicsContains(topic);
	}
}