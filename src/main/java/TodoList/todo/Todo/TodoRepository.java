package TodoList.todo.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAll();
    List<Todo> findAllBySubject(String subject);
}
