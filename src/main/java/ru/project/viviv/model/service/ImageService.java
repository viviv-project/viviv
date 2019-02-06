package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Image;
import ru.project.viviv.model.repository.ImageRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public void createImage(@NotNull Image image) {
        imageRepository.save(image);
    }

    public Image getImageById(@NotNull String id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void removeAllImages() {
        imageRepository.deleteAll();
    }

    public void removeImageById(@NotNull String id) {
        imageRepository.deleteById(id);
    }

    public void removeImage(@NotNull Image image) {
        imageRepository.delete(image);
    }
}