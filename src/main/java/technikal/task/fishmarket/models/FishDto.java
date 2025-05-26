package technikal.task.fishmarket.models;

import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class FishDto {
	

	@NotEmpty(message = "потрібна назва рибки")
	private String name;
	@Min(0)
	private double price;

	@Size(max = 3, message = "Максимум 3 фото")
	private List<MultipartFile> imageFiles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}
}
