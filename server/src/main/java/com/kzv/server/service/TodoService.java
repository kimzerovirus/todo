package com.kzv.server.service;

import com.kzv.server.model.TodoEntity;
import com.kzv.server.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public String testService() {
        return "Test Service";
    }

    public String testService2() {
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder()
                .title("My first todo item")
                .build();
        // TodoEntity 저장
        todoRepository.save(entity);
        // TodoEntity 검색
        TodoEntity saveEntity = todoRepository.findById(entity.getId()).get();
        return saveEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        //validation
        validate(entity);

        todoRepository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return todoRepository.findByUserId(userId);
    }

    // update
    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());

        //반환된 엔티티가 존재한다면 새로 덮어쓰기한다.
        //람다식
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            todoRepository.save(todo);
        });

//        if(original.isPresent()){
//            final TodoEntity todo = original.get();
//            todo.setTitle(entity.getTitle());
//            todo.setDone(entity.isDone());
//
//            //저장
//            todoRepository.save(todo);
//        }

        return retrieve(entity.getUserId());
    }

    //delete
    public List<TodoEntity> delete(final TodoEntity entity){
        validate(entity);

        try{
            todoRepository.delete(entity);
        }catch (Exception e){
            log.error("eerror deleting entity"+ entity.getId());

            throw new RuntimeException("error deleting entity" + entity.getId());
        }

        return retrieve(entity.getUserId());
    }


    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }
}
