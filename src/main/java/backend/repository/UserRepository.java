package backend.repository;

import backend.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User> {
	public Optional<User> findByUserId(long userId);
}
