package com.wikia.webdriver.common.remote.discussions.context;

import com.wikia.webdriver.common.remote.context.RemoteContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ModeratePostContext extends RemoteContext {

  private final String postId;

  @Builder
  public ModeratePostContext(String siteId, String postId) {
    super(siteId);
    this.postId = postId;
  }

  public static ModeratePostContext defaultContextUsing(
      final String siteId, final PostEntity.Data data
  ) {
    Objects.requireNonNull(
        data.getFirstPostId(),
        "First post id is required to report post. Post id is not used in this operation!"
    );

    return ModeratePostContext.builder().siteId(siteId)
        // in order to report post (in front end) it has to be treated as reply (in front end, but post in back end)
        .postId(data.getFirstPostId()).build();
  }
}
