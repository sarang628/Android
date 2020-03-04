/*
 * Copyright (c) 2020. sryang
 */

package com.sarang.profilescreensample;


/**
 *
 */
public class User {

    /**
     Default constructor
     */
    public User() {
    }

    /**
     *
     */
    public int userId = -1;

    /**
     *
     */
    public String userName;

    /**
     *
     */
    public String email;

    /**
     *
     */
    public String login_platform;

    /**
     *
     */
    public String create_date;

    public String access_token;

    public String profile_pic_url;

    public static User fromJson(String s) {
        return new Gson().fromJson(s, User.class);
    }

    public String point = "5050";

    public int review_count;
    public int followers;
    public int following;

    public boolean isFollow;

    public static class Builder {
        User user = new User();

        public Builder userId(int userId) {
            user.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            user.userName = userName;
            return this;
        }

        public Builder profilePic(String url) {
            user.profile_pic_url = url;
            return this;
        }

        public User build() {
            return user;
        }
    }

    public static User getDummy() {
        return new User.Builder()
                .userName("asdf")
                .profilePic("https://pbs.twimg.com/profile_images/1186245554948296710/S9j_reWx_400x400.jpg")
                .build();
    }

}