package TodoList.todo.Todo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    String content;
    @Column(columnDefinition = "TEXT")
    String subject;
    LocalDateTime createTime; //시작일
    LocalDateTime deadLine; //마감일
}
