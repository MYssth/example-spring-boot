package com.example.h2JPADemo.service;

import com.example.h2JPADemo.dao.TutorialRepository;
import com.example.h2JPADemo.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(){
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        tutorialRepository.findAll().forEach(tutorials::add);
        return tutorials;
    }

    public List<Tutorial> getTutorialsWithTitle(String title){
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        return tutorials;
    }

    public Optional<Tutorial> getTutorialById(long id){
        return tutorialRepository.findById(id);
    }

    public Tutorial createTutorial(Tutorial tutorial){
        return tutorialRepository
                .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
    }

    public Optional<Tutorial> updateTutorial(long id, Tutorial tutorial){
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if(tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            tutorialRepository.save(_tutorial);
            return  tutorialData;
        }
        else{
            return null;
        }
    }

    public void deleteTutorial(long id){
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorial(){
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished(){
        return tutorialRepository.findByPublished(true);
    }

}
