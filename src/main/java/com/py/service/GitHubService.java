package com.py.git.automation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import java.util.stream.Collectors;
import com.py.model.GitCommitDto;

@Service
public class GitHubService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN"); // 用於私有 repo / commit

    // 讀公有或私有分支
    public List<String> listBranches(String owner, String repo) {

        try {
            String url = String.format("https://api.github.com/repos/%s/%s/branches", owner, repo);
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/vnd.github+json")
                    .GET();

          
            if (GITHUB_TOKEN != null && !GITHUB_TOKEN.isEmpty()) {
                builder.header("Authorization", "token " + GITHUB_TOKEN);
                
            }

            HttpRequest request = builder.build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body().lines()
                    .filter(l -> l.contains("\"name\":"))
                    .map(l -> l.split(":")[1].trim().replace("\"", "").replace(",", ""))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to list branches", e);
        }
    }

    // 提交檔案（需要 PAT）
    public String commitFile(String owner, String repo, GitCommitDto dto) {
        if (GITHUB_TOKEN == null || GITHUB_TOKEN.isEmpty()) {
            throw new RuntimeException("GitHub PAT is required for commit");
        }

        try {
            String url = String.format("https://api.github.com/repos/%s/%s/contents/%s",
                    owner, repo, dto.getFilePath());

            Map<String, Object> body = new HashMap<>();
            body.put("message", dto.getCommitMessage());
            body.put("content", Base64.getEncoder().encodeToString(dto.getContent().getBytes()));
            body.put("branch", dto.getBranch());
            body.put("sha", dto.getSha());

            String requestBody = new ObjectMapper().writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "token " + GITHUB_TOKEN)
                    .header("Accept", "application/vnd.github+json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Failed to commit file", e);
        }
    }
}