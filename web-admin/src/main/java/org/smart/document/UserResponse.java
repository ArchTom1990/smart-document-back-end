package org.smart.document;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String token;
    private UserData data;

    @Data
    public static class UserData {
        private String name;
        private String avatar;
        private String userid;
        private String email;
        private String signature;
        private String title;
        private String group;
        private List<Tag> tags;
        private Integer notifyCount;
        private Integer unreadCount;
        private String country;
        private Geographic geographic;
        private String address;
        private String phone;
    }

    @Data
    public static class Tag {
        private String key;
        private String label;
    }

    @Data
    public static class Geographic {
        private Location province;
        private Location city;
    }

    @Data
    public static class Location {
        private String label;
        private String key;
    }

}
