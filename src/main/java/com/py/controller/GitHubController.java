package com.py.git.automation.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.py.git.automation.service.GitHubService;
import com.py.model.GitCommitDto;
@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService service;

    public GitHubController(GitHubService service) {
        this.service = service;
    }

    // 列分支 (公有不用 token, 私有可用 token)
    @GetMapping("/{owner}/{repo}/branches")
    public List<String> listBranches(@PathVariable String owner, @PathVariable String repo) {
        return service.listBranches(owner, repo);
    }

    // 提交檔案 (需要 PAT)
    @PutMapping("/{owner}/{repo}/commit")
    public String commitFile(@PathVariable String owner, @PathVariable String repo,
                             @RequestBody GitCommitDto commitDto) {
        System.out.println("收到 SHA: " + commitDto.getSha());                        
        return service.commitFile(owner, repo, commitDto);
    }
}