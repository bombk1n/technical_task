package technikal.task.fishmarket.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technikal.task.fishmarket.models.Fish;
import technikal.task.fishmarket.models.FishDto;
import technikal.task.fishmarket.repositories.FishRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FishService {

    private final FishRepository repo;

    public FishService(FishRepository repo) {
        this.repo = repo;
    }

    public List<Fish> getAllFish() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void deleteFish(int id) {
        try {
            Fish fish = repo.findById(id).get();
            Path imagePath = Paths.get("public/images/" + fish.getImageFileName());
            Files.delete(imagePath);
            repo.delete(fish);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void saveFish(FishDto fishDto) throws IOException {
        MultipartFile image = fishDto.getImageFile();
        Date catchDate = new Date();
        String storageFileName = catchDate.getTime() + "_" + image.getOriginalFilename();

        String uploadDir = "public/images/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
        }

        Fish fish = new Fish();
        fish.setCatchDate(catchDate);
        fish.setImageFileName(storageFileName);
        fish.setName(fishDto.getName());
        fish.setPrice(fishDto.getPrice());

        repo.save(fish);
    }
}
