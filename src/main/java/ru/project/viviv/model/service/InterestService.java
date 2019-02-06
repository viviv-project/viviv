package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Interest;
import ru.project.viviv.model.repository.InterestRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;

    public void createInterest(@NotNull Interest interest) {
        interestRepository.save(interest);
    }

    public Interest getInterestById(@NotNull String id) {
        return interestRepository.findById(id).orElse(null);
    }

    public Interest getInterestByName(@NotNull String name) {
        return interestRepository.findByInterest(name);
    }

    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public void removeAllInterests() {
        interestRepository.deleteAll();
    }

    public void removeInterestById(@NotNull String id) {
        interestRepository.deleteById(id);
    }

    public void removeInterest(@NotNull Interest interest) {
        interestRepository.delete(interest);
    }
}