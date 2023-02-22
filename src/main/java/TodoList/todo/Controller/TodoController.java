package TodoList.todo.Controller;

import TodoList.todo.Todo.Todo;
import TodoList.todo.Todo.TodoRepository;
import TodoList.todo.Todo.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/todo")
@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @PostMapping("/create")
        public String createTodo(@RequestParam("content") String content) {
        this.todoService.create(content);
        return "redirect:/todo/list";
    }

    @GetMapping("/list")
    public String todoList(Model model)
    {
        List<Todo> todoList=todoService.findAll();
        model.addAttribute("list",todoList);
        return "todo_list";
    }
    @PostMapping("/delete/{id}")
    public String todoDelete(@PathVariable("id") Long id){
        Todo delTodo=this.todoService.getTodo(id);
        this.todoService.delete(delTodo);
        return "redirect:/todo/list";
    }
    @GetMapping("/modify/{id}")
    public String todoModify(Model model,@PathVariable("id") Long id){
        model.addAttribute("id",id);
        return "todo_modify";
    }
    @PostMapping("/modify/{id}")
    public String todoModify(@PathVariable("id") Long id,@RequestParam("content") String content){
        this.todoService.modify(todoService.getTodo(id),content );
        return "redirect:/todo/list";
    }

}
