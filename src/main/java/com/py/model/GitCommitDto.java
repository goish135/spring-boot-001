package com.py.model;

public class GitCommitDto {

    private String filePath;
    private String content;
    private String commitMessage;
    private String branch;
    private String sha; // 用於更新檔案時的參數

    public GitCommitDto() {}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }    
}