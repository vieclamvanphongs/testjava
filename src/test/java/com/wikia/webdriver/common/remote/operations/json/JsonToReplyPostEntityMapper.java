package com.wikia.webdriver.common.remote.operations.json;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntityData;

import com.jayway.jsonpath.DocumentContext;

public class JsonToReplyPostEntityMapper {

  private final DocumentContext json;

  public JsonToReplyPostEntityMapper(DocumentContext json) {
    this.json = json;
  }

  public ReplyEntityData toData() {
    return ReplyEntityData.builder()
        .id(json.read("$.id"))
        .threadId(json.read("$.threadId"))
        .body(json.read("$.rawContent"))
        .authorId(json.read("$.creatorId"))
        .build();
  }
}
