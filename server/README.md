

### @NoArgsConstructor
매개변수가 없는 생성자
```
public TodoEntity(){

} 
```
### @ AllArgsConstructor
클래스의 모든 멤버 변수를 매개변수로 받는 생성자
```
public TodoEntity()(String id, String userId, String title, boolean done){
    super();
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.done = done;
}
```

### @Data
Getter/Setter 구현하는 롬복 어노테이션
```
private String getId(){
    reutrn id = id;
}
private String setId(String id){
    this.id = id;
}
public String getUserId(){
    return userId;
}
public void setUserId(String userId){
    this.userId = userId;
}
```
