package get;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PetStore {


    @Test
    public void getPetTest() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        HttpResponse response = httpClient.execute(httpGet);

        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        Assert.assertEquals(200, statusCode);


        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);




    }
}
