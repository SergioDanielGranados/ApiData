package org.data.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemServiceRestClient {

  private final String BASE_URL = "https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1/items";

  private final RestTemplate restTemplate;

  @Autowired
  public ItemServiceRestClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getAll() {
    return restTemplate.getForObject(BASE_URL, String.class);
  }
}
