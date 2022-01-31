

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

### 스프릥 시큐리티 & 서블릿 필터
 1. 로그인시 유저정보를 바탕으로 secret키로 서명 *[헤더].[페이로드].XXX* 를 Base64로 인코딩 후 토큰반환
 2. 클라이언트에서 받은 토큰을 Base64로 디코딩 후 결과 *[헤더].[페이로드].XXX* 에서 *[헤더].[페이로드]* 만으로 secret키로 전자서명 
 3. 디코딩된 토큰의 마지막부분과 *2.* 에서 만든 전자서명의 결과를 비교... 일치할 경우 검증완료