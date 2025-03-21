package com.example.spring15.service;



import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.spring15.dto.GeminiRequest;
import com.example.spring15.dto.GeminiResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import reactor.core.publisher.Mono;

@Service
public class GeminiService2 {
    private final WebClient webClient;
    private final Gson gson = new Gson();
    private final String apiKey;
    private final String url;
    
    //생성자에 필요한 data 3 개 전달받기 
    public GeminiService2(WebClient.Builder builder, 
    		@Value("${gemini.key}") String apiKey,
    		@Value("${gemini.url}") String url  //서비스 객체가 생성되는 시점에 @Value 가 읽어와 지지 않는다.
    ) {
    	//전달 받은 내용을 필드에 저장하기 
	   this.apiKey=apiKey;
	   this.url=url;
	   this.webClient=builder.baseUrl(url).build();
    }
    
    public Mono<String> quiz2(Map<String, String> map){
    	//map에는 quiz와 answer가 담겨있다.
    	// Gemini api 요청을 통해서 해당 answer가 맞으면 {"isCorrect":true, "comment":"code 피드백"} 틀리면 {"isCorrect":false, "comment": "틀린이유설명"} 형식의 json 문자열을 Gemini가 응답하도록 프로그래밍 해보세요 
    	String str="""
    			클라이언트가 입력한 답 : ""
    			
    			클라이언트가 입력한 답의 정답여부를 boolean 타입으로, 정답여부에 따른 comment는 마크다운 형식의 String 타입으로 반환해.
    			사용할 형식은 답이 정답일 때 {"isCorrect":true, "comment":""} 형식이고, comment에는 code에 대한 피드백(참 잘했어요 등)을 반환해줘.
    			답이 틀렸을 때 {"isCorrect":false, "comment":""}이고, comment에는 code가 틀린 이유를 반환해줘. 
    			
    			""".formatted(map.get("quiz"), map.get("answer"));
    	return getChatResponse(str);
    }

    public Mono<String> quiz(Map<String,String> map){
    	//map에는 quiz와 answer가 담겨있다. gemini api 요청을 통해서 해당 answer가 맞으면 {"isCorrect":true} 틀리면 {"isCorrect":false}형식의 json 문자열을 Gemini가 응답하도록 프로그래밍 해보기
    	
    	String str="""
    			퀴즈의 질문 : "%s"
    			클라이언트가 입력한 답 : "%s"
    			
    			클라이언트가 입력한 답의 정답여부를 boolean 타입으로 반환해.
    			반환할 때 응답형식은 맞는 정답일 때 {"isCorrect":true} 틀린 정답일 때 {"isCorrect:false"}로 반환해줘. 
    			설명없이 json 객체만 반환해줘.   			
    			""".formatted(map.get("quiz"), map.get("answer"));
    	return getChatResponse(str);
    		
    }
    
    public Mono<String> getFoodCategory(String food) {
        String str = """
            클라이언트가 입력한 음식: "%s"
            
            해당 음식의 카테고리를 JSON 형식으로 반환해.
            응답은 아래 형식을 따라야 해:
            { "category": "한식" }
            
            ["한식", "중식", "일식", "양식", "기타"] 중 하나만 "category" 값으로 넣어줘.
            설명 없이 JSON 객체만 반환해.
            markdown 형식으로 응답하면 안되.
        """.formatted(food);

        return getChatResponse(str);
    }

    public String getChatResponseSync(String prompt) {
    	//GeminiRequest 구성하기 
        GeminiRequest request = new GeminiRequest();
        GeminiRequest.Content content = new GeminiRequest.Content();
        GeminiRequest.Part part = new GeminiRequest.Part();
        //part 에 질문을 담는다. 
        part.setText(prompt);
        content.setParts(List.of(part));
        request.setContents(List.of(content));

        String result =  webClient.post()
                .uri(uriBuilder -> uriBuilder.path(":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request) // Map 객체 대신에 GeminiRequest 객체를 넣어주면 json 으로 변경된다.
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(responseBody -> System.out.println(responseBody))
                .flatMap(responseBody -> {
                    try {
                        return Mono.just(extractResponse(responseBody));
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("JSON 파싱 오류", e));
                    }
                }).block();//block 메소드를 호출하면 결과 문자열을 받아올때까지 기다린다(굳이 비동기 동작이 필요없다면)
        return result;
    }
    
 // 질문을 던지면 Mono<String>객체를 리턴하는 메소드
 	public Mono<String> getChatResponse(String prompt) {
 		// 요청의 body 구성하기
 		Map<String, Object> reqeustBody = Map.of("contents", List.of(Map.of("parts", List.of(Map.of("text", prompt))))); // 생김새는
 																															// 마치
 																															// {"contents":[{"parts":["text":"질문"}]}]}
 																															// 형태
 		Mono<String> mono = webClient.post()
 				.uri(uriBuilder -> uriBuilder.path(":generateContent").queryParam("key", apiKey).build())
 				.contentType(MediaType.APPLICATION_JSON).bodyValue(reqeustBody).retrieve().bodyToMono(String.class)
 				.doOnNext(responseBody -> System.out.println(responseBody)).flatMap(responseBody -> {
 					try {
 						//String result=extractResponse(responseBody);
 						//만일 문자열의 앞과 뒤에 ```json, ```이 존재한다면 제거하기
 						//String escaped =result.replaceAll("^```json\\s*","").replaceAll("\\s&```$", "");
 						//return Mono.just(extractResponse(escaped));
 						return Mono.just(responseBody);
 					} catch (Exception e) {
 						return Mono.error(new RuntimeException("JSON 파싱 오류", e));
 					}
 				});
 		System.out.println("서비스 메소드가 리턴됨");
 		return mono;
 	}
    
    //응답된 json 을 파싱해서 문자열 얻어내는 메소드 
    private String extractResponse(String responseJson) {
        try {
            GeminiResponse geminiResponse = gson.fromJson(responseJson, GeminiResponse.class);

            return Optional.ofNullable(geminiResponse)
                    .map(GeminiResponse::getCandidates)
                    .filter(candidates -> !candidates.isEmpty())
                    .map(candidates -> candidates.get(0))
                    .map(GeminiResponse.Candidate::getContent)
                    .map(GeminiResponse.Content::getParts)
                    .filter(parts -> !parts.isEmpty())
                    .map(parts -> parts.stream().map(GeminiResponse.Part::getText).collect(Collectors.joining("\n")))
                    .orElse("응답 없음");

        } catch (JsonSyntaxException e) {
            return "JSON 파싱 오류: " + e.getMessage();
        }
    }
}