package com.aptechph.task_management_system;

import com.aptechph.task_management_system.tasks.model.Poll;
import com.aptechph.task_management_system.tasks.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class DataSeeder {

    @Autowired
    private PollRepository pollRepository;

    @PostConstruct
    public void seedData() {
        if (pollRepository.count() == 0) {
            Poll poll1 = new Poll();
            poll1.setQuestion("What is your favorite programming language?");
            poll1.setOptions("Java,Python,C++,JavaScript");
            pollRepository.save(poll1);

            Poll poll2 = new Poll();
            poll2.setQuestion("Which season do you like the most?");
            poll2.setOptions("Spring,Summer,Fall,Winter");
            pollRepository.save(poll2);
        }
    }
}
