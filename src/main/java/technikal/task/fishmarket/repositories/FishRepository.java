package technikal.task.fishmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import technikal.task.fishmarket.models.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {

}
