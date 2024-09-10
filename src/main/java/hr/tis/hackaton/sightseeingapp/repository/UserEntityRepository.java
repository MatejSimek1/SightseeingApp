package hr.tis.hackaton.sightseeingapp.repository;

import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);

}
