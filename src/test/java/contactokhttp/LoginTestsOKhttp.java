package contactokhttp;

import com.google.gson.Gson;
import datatransferobject.AuthRequestDto;
import datatransferobject.AuthResponseDto;
import datatransferobject.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestsOKhttp {

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");


    @Test
    public void loginTest() throws IOException {

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("noa@gmail.com")
                .password("Nnoa12345$")
                .build();

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());

        AuthResponseDto responseDto =
                gson.fromJson(response.body().string(),AuthResponseDto.class);

        String token = responseDto.getToken();

        System.out.println(token);

        Assert.assertEquals(response.code(), 200);


    }


    @Test
    public void loginWrongEmail() throws IOException{
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("noa@gmail.com")
                .password("Nnoa1245$")
                .build();

        Gson gson = new Gson();

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);    // nuzna tolko togda kogda estj body

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        boolean successful = response.isSuccessful();   // waiting for false
        Assert.assertFalse(successful);

        System.out.println(response.code());

        Assert.assertEquals(response.code(), 401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);

        System.out.println("-----------------------------------");
        System.out.println(errorDto.getMessage());
        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getDetails());


    }



}
