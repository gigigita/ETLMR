package com.dapeng.json;

/**
 * @Author Administrator
 * @Date 2020/10/21 9:28
 * @Version 1.0
 * description
 */
public class DetailBean {

    private String ChapterNode;
    private String HandIn;
    private String Score;
    private String HandInNum;

    public DetailBean() {
    }

    public DetailBean(String chapterNode, String handIn, String score, String handInNum) {
        ChapterNode = chapterNode;
        HandIn = handIn;
        Score = score;
        HandInNum = handInNum;
    }

    public String getChapterNode() {
        return ChapterNode;
    }

    public void setChapterNode(String chapterNode) {
        ChapterNode = chapterNode;
    }

    public String getHandIn() {
        return HandIn;
    }

    public void setHandIn(String handIn) {
        HandIn = handIn;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getHandInNum() {
        return HandInNum;
    }

    public void setHandInNum(String handInNum) {
        HandInNum = handInNum;
    }

    @Override
    public String toString() {
        return "DetailBean{" +
                "ChapterNode='" + ChapterNode + '\'' +
                ", HandIn='" + HandIn + '\'' +
                ", Score='" + Score + '\'' +
                ", HandInNum='" + HandInNum + '\'' +
                '}';
    }
}
