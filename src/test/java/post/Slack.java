package post;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.PayloadUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Slack {

    @Test
    public void sendMessageTest() throws URISyntaxException, IOException {

        CloseableHttpClient httpClient=HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder("https://slack.com/api/chat.postMessage");
        HttpPost post=new HttpPost(uriBuilder.build());
        post.setHeader("Content-Type","application/json");
        post.setHeader("Accept","application/json");
        post.setHeader("Authorization",
                "Bearer xoxb-1717144054548-2225306696261-wDWBqOUIhTzF4rAanFvdThNH");

        HttpEntity entity=new StringEntity(PayloadUtils.getSlackMessage("C026Q9VVD42",
                "Aizada: message from Java"));

        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //we didn/t provide all keys,it gives us excepton,to avoid this we are adding line 42
        SlackPojo slackResponce = objectMapper.readValue(response.getEntity().getContent(), SlackPojo.class);

       Assert.assertTrue(slackResponce.isOk());





    }
}
