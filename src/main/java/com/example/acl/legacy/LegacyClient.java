package com.example.acl.legacy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class LegacyClient {
  private final WebClient webClient;
  public LegacyClient(WebClient.Builder builder, @Value("${legacy.base-url}") String baseUrl){
    this.webClient = builder.baseUrl(baseUrl).build();
  }
  public Mono<LegacyOrderResponse> getOrderById(String id){
    return webClient.get()
      .uri(uri -> uri.path("/v1/pedidos").queryParam("id", id).build())
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new LegacyNotFoundException(id)))
      .onStatus(HttpStatus::is5xxServerError, r -> Mono.error(new LegacyUnavailableException()))
      .bodyToMono(LegacyOrderResponse.class);
  }
}