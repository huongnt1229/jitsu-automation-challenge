package my.tests;

import io.restassured.response.Response;
import my.pages.GithupApiSteps;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GitHubAPITests {
    private static final String BASE_URL = "https://api.github.com/orgs/SeleniumHQ/repos";

    @Test
    public void testGitHubRepoData() {
        GithupApiSteps gitHubAPISteps = new GithupApiSteps();
        //Send Get request to get the response
        Response response = gitHubAPISteps.getRequest(BASE_URL);
        Assert.assertEquals(response.getStatusCode(), 200, "API response is not 200 OK");

        JSONArray repos = new JSONArray(response.getBody().asString());

        //Get total open issue
        int totalOpenIssues = gitHubAPISteps.getTotalOpenIssues(repos);
        System.out.println("Total Open Issues: " + totalOpenIssues);

        //Sort the repositories by date updated in descending order.
        List<JSONObject> sortedRepos = gitHubAPISteps.sortRepositoriesByUpdatedAt(repos);
        System.out.println("Most recently updated repository: " + sortedRepos.get(0).getString("name"));

        //Get the most watched repository
        JSONObject mostWatchedRepo = gitHubAPISteps.getMostWatchedRepository(repos);
        System.out.println("Repository with most watchers: " + mostWatchedRepo.getString("name") +
                " (Watchers: " + mostWatchedRepo.getInt("watchers_count") + ")");
    }
}
