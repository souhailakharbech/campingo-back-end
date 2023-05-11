package webuild.esprit.tn.tunisiacampwebapplication.api;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.FacebookType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FacebookService {

    private final String appId = "223975676864691";
    private final String appSecret = "1b3b7d20a12313bf7660f94773cf0db2";
    private final String redirectUri = "http://localhost:8095/Campi/AUTH/auth/facebook/callback";
    private String accessToken;
    private Version version = Version.LATEST;

    @GetMapping("/AUTH/auth/facebook/callback")
    public void handleCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        FacebookClient.AccessToken accessToken = new DefaultFacebookClient(version).obtainUserAccessToken(
                appId, appSecret, redirectUri, code);
        this.accessToken = accessToken.getAccessToken();
        response.sendRedirect("/");
    }

    @GetMapping("/AUTH/auth/facebook/post")
    public void sharePost(HttpServletResponse response) throws IOException {
        // create a Facebook client object
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, version);

        // build the post request
        FacebookType postResponse = facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", "This is a test post."));

        // print the post ID if the request was successful
        if (postResponse != null) {
            System.out.println("Successfully posted to Facebook with post ID " + postResponse.getId());
        }

        response.sendRedirect("/");
    }

    @RequestMapping("/AUTH/auth/")
    public String home() {
        return "home";
    }

    @GetMapping("/AUTH/auth/facebook/login")
    public void login(HttpServletResponse response) throws IOException {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_TO_GROUPS);
        scopeBuilder.addPermission(FacebookPermissions.USER_POSTS);
        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);

        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_VIDEO);
        String authorizationUrl = "https://www.facebook.com/v12.0/dialog/oauth?client_id=" + appId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code&scope=" + scopeBuilder.toString();
        response.sendRedirect(authorizationUrl);
    }
}



