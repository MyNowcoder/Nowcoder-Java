package com.lin.community.service;


import com.lin.community.dto.CommentInfo;
import com.lin.community.dto.enums.CommentKindEnum;

import java.util.List;

public interface CommentService {
    List<CommentInfo> getCommentByEntityWithUser(int entityId, int offset, int limit);
    int getCountByEntity(int entityId,CommentKindEnum commentKind);
}
