package TodoList.todo.Controller;

import TodoList.todo.Todo.Todo;
import TodoList.todo.Todo.TodoRepository;
import TodoList.todo.Todo.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/todo")
@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/create")
    public String createTodo(){
        return "todo_create";
    }
    @PostMapping("/create")
        public String createTodo(@RequestParam("content") String content,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("deadLine") String deadLine) {
        LocalDate ld=LocalDate.parse(deadLine);
        LocalDateTime dl = LocalDateTime.of(ld, LocalTime.now());
        this.todoService.create(subject,content,dl);
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
        model.addAttribute("todo",todoService.getTodo(id));
        return "todo_modify";
    }
    @PostMapping("/modify/{id}")
    public String todoModify(@PathVariable("id") Long id,@RequestParam("content") String content,
                             @RequestParam("subject") String subject){
        this.todoService.modify(todoService.getTodo(id),subject,content );
        return "redirect:/todo/list";
    }
    @GetMapping("/search")
    public String todoSearch(Model model,@RequestParam("query") String query)
    {
        if(query.equals(""))
        {
            model.addAttribute("list",this.todoService.findAll());
        }
        else {
            List<Todo> searchTodo = this.todoService.findBySubject(query);
            model.addAttribute("list", searchTodo);
        }

        return "todo_list";
    }

}
