package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {

	//@Autowried
	//private PostBO postBO;
	
	@GetMapping("/timeline")
	public String timeline() {
		return "timeline/timeline";
	}
}
