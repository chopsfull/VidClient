
package com.example.chopsfull_.test_task_tereshchuk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("full_url")
    @Expose
    private String fullUrl;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("avatar_ai")
    @Expose
    private String avatarAi;
    @SerializedName("cover")
    @Expose
    private Object cover;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("cover_ai")
    @Expose
    private String coverAi;
    @SerializedName("displayname")
    @Expose
    private Object displayname;
    @SerializedName("follower_count")
    @Expose
    private int followerCount;
    @SerializedName("likes_count")
    @Expose
    private String likesCount;
    @SerializedName("video_count")
    @Expose
    private int videoCount;
    @SerializedName("video_views")
    @Expose
    private String videoViews;
    @SerializedName("videos_scores")
    @Expose
    private int videosScores;
    @SerializedName("comments_scores")
    @Expose
    private int commentsScores;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("enabled")
    @Expose
    private String enabled;
    @SerializedName("ga_id")
    @Expose
    private Object gaId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("sub_enabled")
    @Expose
    private boolean subEnabled;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param enabled
     * @param videosScores
     * @param fullUrl
     * @param videoCount
     * @param coverUrl
     * @param videoViews
     * @param likesCount
     * @param avatar
     * @param gaId
     * @param username
     * @param cover
     * @param bio
     * @param email
     * @param coverAi
     * @param subEnabled
     * @param userId
     * @param avatarUrl
     * @param avatarAi
     * @param followerCount
     * @param commentsScores
     * @param displayname
     */
    public User(String userId, String username, String fullUrl, Object avatar, String avatarUrl, String avatarAi, Object cover, String coverUrl, String coverAi, Object displayname, int followerCount, String likesCount, int videoCount, String videoViews, int videosScores, int commentsScores, Object bio, String enabled, Object gaId, String email, boolean subEnabled) {
        super();
        this.userId = userId;
        this.username = username;
        this.fullUrl = fullUrl;
        this.avatar = avatar;
        this.avatarUrl = avatarUrl;
        this.avatarAi = avatarAi;
        this.cover = cover;
        this.coverUrl = coverUrl;
        this.coverAi = coverAi;
        this.displayname = displayname;
        this.followerCount = followerCount;
        this.likesCount = likesCount;
        this.videoCount = videoCount;
        this.videoViews = videoViews;
        this.videosScores = videosScores;
        this.commentsScores = commentsScores;
        this.bio = bio;
        this.enabled = enabled;
        this.gaId = gaId;
        this.email = email;
        this.subEnabled = subEnabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarAi() {
        return avatarAi;
    }

    public void setAvatarAi(String avatarAi) {
        this.avatarAi = avatarAi;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverAi() {
        return coverAi;
    }

    public void setCoverAi(String coverAi) {
        this.coverAi = coverAi;
    }

    public Object getDisplayname() {
        return displayname;
    }

    public void setDisplayname(Object displayname) {
        this.displayname = displayname;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public String getVideoViews() {
        return videoViews;
    }

    public void setVideoViews(String videoViews) {
        this.videoViews = videoViews;
    }

    public int getVideosScores() {
        return videosScores;
    }

    public void setVideosScores(int videosScores) {
        this.videosScores = videosScores;
    }

    public int getCommentsScores() {
        return commentsScores;
    }

    public void setCommentsScores(int commentsScores) {
        this.commentsScores = commentsScores;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Object getGaId() {
        return gaId;
    }

    public void setGaId(Object gaId) {
        this.gaId = gaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubEnabled() {
        return subEnabled;
    }

    public void setSubEnabled(boolean subEnabled) {
        this.subEnabled = subEnabled;
    }

}
