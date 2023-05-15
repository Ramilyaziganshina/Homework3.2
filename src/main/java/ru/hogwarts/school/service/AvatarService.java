package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface AvatarService {
    Avatar findAvatar(long studentId);
    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;
    String getExtension(String fileName);
    byte[] generateImagePreview(Path filePath) throws IOException;
    Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);
}
