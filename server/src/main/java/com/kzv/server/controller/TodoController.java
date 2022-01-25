package com.kzv.server.controller;

import com.kzv.server.dto.ResponseDto;
import com.kzv.server.dto.TodoDto;
import com.kzv.server.model.TodoEntity;
import com.kzv.server.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto) {
        try {
            String temporaryUserId = "temporary-user";

            // 1. TodoEntity로 변환한다.
            TodoEntity entity = TodoDto.toEntity(dto);

            // 2. id를  null로 초기화... 생성당시에는 id가 없어야 하기 때문
            entity.setId(null);

            // 3. 임시 사용자 아이디를 설정
            entity.setUserId(temporaryUserId);

            // 4. 서비스를 통해 todo엔티티 생성
            List<TodoEntity> entities = service.create(entity);

            // 5. 자바 스트림을 이용해 엔티티 리턴을 TodoDto 리스트로 변환
            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

            // 6. 변환된 TodoDto 리스트를 이용해 ResponseDto 초기화
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();

            // 7. ResponseDto를 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // 8. 예외의 경우, dto 데이터 대신 error메시지 반환
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary-use";

        // 1. Todo 리스트 가져오기
        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        // 2. 엔티티리스트 -> TodoDto 리스트로 변환
//        List<TodoDto> dtos = entities.stream().map(todo -> new TodoDto(todo)).collect(Collectors.toList());
        // 람다 더블콜론
        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

        // 3. 변환된 리스트로 ResponseDto 초기화
        ResponseDto<TodoDto> res = ResponseDto.<TodoDto>builder().data(dtos).build();

        // 4. dto리턴
        return ResponseEntity.ok().body(res);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDto dto) {
        String temporaryUserId = "temporary-user";

        // dto -> entity 변환
        TodoEntity entity = TodoDto.toEntity(dto);

        entity.setUserId(temporaryUserId);

        List<TodoEntity> entities = service.update(entity);

        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

        ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDto dto) {
        try {
            String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = service.delete(entity);

            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

            ResponseDto<TodoDto> res = ResponseDto.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> res = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.ok().body(res);
        }
    }
}
