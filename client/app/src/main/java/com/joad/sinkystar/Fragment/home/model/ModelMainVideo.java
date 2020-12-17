package com.joad.sinkystar.Fragment.home.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ModelMainVideo {
    /**
     * authorImgUrl : https://p3-dy.byteimg.com/aweme/100x100/1e19c00014b66991215ba.jpeg
     * authorName : 相相啊
     * authorSex : 0
     * coverImgUrl : http://p9-dy.byteimg.com/large/tos-cn-p-0015/13f8180ea2bd44779449b82702b4e47b.jpeg
     * createTime : 1571047431000
     * dynamicCover : https://p9-dy.byteimg.com/obj/tos-cn-p-0015/9991ae92508340ae9b71f132b52a6c70
     * filterMusicNameStr : 青柠（手机摄影）@卡点音乐模版08
     * filterTitleStr : 昨天刷到抖音今天就马不停蹄去买了好多人排队#南洋大师...
     * filterUserNameStr : 相相啊
     * formatLikeCountStr :
     * formatPlayCountStr : 82
     * formatTimeStr : 2019.10.14
     * likeCount : 301
     * musicAuthorName : 青柠（手机摄影）
     * musicImgUrl : https://p3-dy.byteimg.com/aweme/100x100/2dce4000796963dec1a63.jpeg
     * musicName : 卡点音乐模版08
     * playCount : 82
     * title : 昨天刷到抖音今天就马不停蹄去买了  好多人排队  #南洋大师傅 @抖音小助手
     * type : 0
     * videoDownloadUrl : https://aweme.snssdk.com/aweme/v1/play/?video_id=v0300fd00000bmi4fdabr0flkdiivurg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme
     * videoHeight : 720
     * videoPlayUrl : http://v1-dy.ixigua.com/4413f7d529ce8e0d0a60ce24e84ab535/5da4a52a/video/m/220b506942d480c47ada07abbc83b49a4581163dc7510000041d7a9a627d/?a=1128&br=1570&cr=0&cs=0&dr=0&ds=6&er=&l=20191014234102010155051013879290&lr=&rc=anhsZ28zZWc3cDMzM2kzM0ApZ2YzOjo5NDszNzY6ZDo5O2dvc2ZoY29eY2ZfLS1hLTBzcy4xM2IuMi1jLy8zMC8wMzA6Yw%3D%3D
     * videoWidth : 720
     */

    public String authorImgUrl;
    public String authorName;
    public int authorSex;
    public String coverImgUrl;
    public long createTime;
    public String dynamicCover;
    public String filterMusicNameStr;
    public String filterTitleStr;
    public String filterUserNameStr;
    public String formatLikeCountStr;
    public String formatPlayCountStr;
    public String formatTimeStr;
    public int likeCount;
    public String musicAuthorName;
    public String musicImgUrl;
    public String musicName;
    public int playCount;
    public String title;
    public int type;
    public String videoDownloadUrl;
    public int videoHeight;
    public String videoPlayUrl;
    public int videoWidth;

    public ModelMainVideo(String title) {
        this.title = title;
    }

    public static List<ModelMainVideo> arrayTiktokBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ModelMainVideo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getAuthorImgUrl() {
        return authorImgUrl;
    }

    public void setAuthorImgUrl(String authorImgUrl) {
        this.authorImgUrl = authorImgUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorSex() {
        return authorSex;
    }

    public void setAuthorSex(int authorSex) {
        this.authorSex = authorSex;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDynamicCover() {
        return dynamicCover;
    }

    public void setDynamicCover(String dynamicCover) {
        this.dynamicCover = dynamicCover;
    }

    public String getFilterMusicNameStr() {
        return filterMusicNameStr;
    }

    public void setFilterMusicNameStr(String filterMusicNameStr) {
        this.filterMusicNameStr = filterMusicNameStr;
    }

    public String getFilterTitleStr() {
        return filterTitleStr;
    }

    public void setFilterTitleStr(String filterTitleStr) {
        this.filterTitleStr = filterTitleStr;
    }

    public String getFilterUserNameStr() {
        return filterUserNameStr;
    }

    public void setFilterUserNameStr(String filterUserNameStr) {
        this.filterUserNameStr = filterUserNameStr;
    }

    public String getFormatLikeCountStr() {
        return formatLikeCountStr;
    }

    public void setFormatLikeCountStr(String formatLikeCountStr) {
        this.formatLikeCountStr = formatLikeCountStr;
    }

    public String getFormatPlayCountStr() {
        return formatPlayCountStr;
    }

    public void setFormatPlayCountStr(String formatPlayCountStr) {
        this.formatPlayCountStr = formatPlayCountStr;
    }

    public String getFormatTimeStr() {
        return formatTimeStr;
    }

    public void setFormatTimeStr(String formatTimeStr) {
        this.formatTimeStr = formatTimeStr;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getMusicAuthorName() {
        return musicAuthorName;
    }

    public void setMusicAuthorName(String musicAuthorName) {
        this.musicAuthorName = musicAuthorName;
    }

    public String getMusicImgUrl() {
        return musicImgUrl;
    }

    public void setMusicImgUrl(String musicImgUrl) {
        this.musicImgUrl = musicImgUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVideoDownloadUrl() {
        return videoDownloadUrl;
    }

    public void setVideoDownloadUrl(String videoDownloadUrl) {
        this.videoDownloadUrl = videoDownloadUrl;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }
}


