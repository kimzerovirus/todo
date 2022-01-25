package com.kzv.server.service;

import com.kzv.server.model.TodoEntity;
import com.kzv.server.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public String testService(){
        return "Test Service";
    }

    public String testService2(){
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

}
