package my.pages;

import my.core.BaseAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GithupApiSteps extends BaseAPI {
    public int getTotalOpenIssues(JSONArray repos) {
        int totalIssues = 0;
        for (int i = 0; i < repos.length(); i++) {
            totalIssues += repos.getJSONObject(i).getInt("open_issues_count");
        }
        return totalIssues;
    }

    public List<JSONObject> sortRepositoriesByUpdatedAt(JSONArray repos) {
        return repos.toList().stream()
                .map(obj -> new JSONObject((java.util.Map<?, ?>) obj))
                .sorted(Comparator.comparing(repo -> repo.getString("updated_at"), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public JSONObject getMostWatchedRepository(JSONArray repos) {
        return repos.toList().stream()
                .map(obj -> new JSONObject((java.util.Map<?, ?>) obj))
                .max(Comparator.comparing(repo -> repo.getInt("watchers_count")))
                .orElseThrow(() -> new RuntimeException("No repositories found"));
    }

}
