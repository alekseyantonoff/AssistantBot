package io.assistant.AssistantBot.repository;

import io.assistant.AssistantBot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
