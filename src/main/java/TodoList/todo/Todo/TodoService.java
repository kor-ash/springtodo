package TodoList.todo.Todo;

import TodoList.todo.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public Todo getTodo(Long id){
        Optional<Todo> ot=this.todoRepository.findById(id);
        if(ot.isPresent())
            return ot.get();
        else
            throw new DataNotFoundException("Item Not found");
    }
    public List<Todo> getList(){
            return this.todoRepository.findAll();
    }
    public void create(String content){
        Todo todo=new Todo();
        todo.setContent(content);
        todo.setCreateTime(LocalDateTime.now());
        this.todoRepository.save(todo);
    }
    public List<Todo> findAll(){
        return this.todoRepository.findAll();
    }

    public void delete(Todo todo){
        this.todoRepository.delete(todo);
    }
    public void modify(Todo todo,String content){
        todo.setContent(content);
        this.todoRepository.save(todo);
    }
}
