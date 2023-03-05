package com.example.forum.utilities;

import com.example.forum.model.Category;
import com.example.forum.model.Reply;
import com.example.forum.model.Topic;
import com.example.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static Map<String, Object> buildUserMap(User u, List<Category> categories) {
        Map<String, Object> userMap = buildUserMap(u);

        Map<String, Object> permissionMap = new HashMap<>();
        permissionMap.put("root", new String[]{"own_topics:write", "own_topics:delete",
                "own_replies:write", "own_replies:delete", "categories:write", "categories:delete"});
        permissionMap.put("categories", categories);

        userMap.put("permissions", permissionMap);

        return userMap;
    }

    public static Map<String, Object> buildUserMap(User u) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("role", u.getRole());
        userMap.put("_id", u.getId());
        userMap.put("email", u.getEmail());
        userMap.put("name", u.getName());
        userMap.put("__v", 0);
        userMap.put("avatarurl", "");
        userMap.put("id", u.getId());

        return userMap;
    }

    public static Map<String, Object> buildCategoryMap(Category c) {
        Map<String, Object> map = new HashMap<>();

        map.put("color", c.getColor());
        map.put("description", c.getDescription());
        map.put("moderators", new ArrayList<>());
        map.put("slug", c.getSlug());
        map.put("title", c.getTitle());
        map.put("__v", 0);
        map.put("_id", c.getId());

        return map;
    }

    public static String generateRandomColor() {
        int value = (int) (Math.random() * 360);

        return "hsl(" + value + ", 50%, 50%)";
    }

    public static Map<String, Object> buildTopicMap(Topic topic, List<Object> replies) {
        Map<String, Object> map = new HashMap<>();

        map.put("views", topic.getViews());
        map.put("_id", topic.getId());
        map.put("title", topic.getTitle());
        map.put("content", topic.getContent());
        map.put("createdAt", topic.getCreatedAt());
        map.put("updatedAt", topic.getUpdatedAt());
        map.put("__v", 0);

        map.put("replies", replies);

        map.put("numberOfReplies", replies.size());

        map.put("category", buildCategoryMap(topic.getCategory()));
        map.put("user", buildUserMap(topic.getUser()));

        return map;
    }

    public static Map<String, Object> buildReplyMap(Reply reply) {
        Map<String, Object> map = new HashMap<>();

        map.put("_id", reply.getId());
        map.put("content", reply.getContent());
        map.put("topic", reply.getTopic().getId());
        map.put("createdAt", reply.getCreatedAt());
        map.put("updatedAt", reply.getUpdatedAt());
        map.put("__v", 0);

        map.put("user", buildUserMap(reply.getUser()));

        return map;
    }
}
