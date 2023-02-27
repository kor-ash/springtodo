package TodoList.todo.Todo;

import TodoList.todo.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void create(String subject,String content,LocalDateTime deadLine){
        Todo todo=new Todo();
        todo.setSubject(subject);
        todo.setContent(content);
        todo.setCreateTime(LocalDateTime.now());
        todo.setDeadLine(deadLine);
        this.todoRepository.save(todo);
    }
    public List<Todo> findAll(){
        List<Todo> todoList=this.todoRepository.findAll();
        todoList=todoList.stream().
                sorted(Comparator.comparing(Todo::getDeadLine))
                .collect(Collectors.toList());
        return todoList;
    }

    public void delete(Todo todo){
        this.todoRepository.delete(todo);
    }
    public void modify(Todo todo,String subject,String content){
        todo.setContent(content);
        todo.setSubject(subject);
        this.todoRepository.save(todo);
    }
    public List<Todo> findBySubject(String subject)
    {
        return this.todoRepository.findAllBySubject(subject);
    }
}
