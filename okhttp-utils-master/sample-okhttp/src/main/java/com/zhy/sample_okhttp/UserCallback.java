package com.zhy.sample_okhttp;

import com.google.gson.Gson;
import okhttp3.Response;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class UserCallback extends Callback<Users>
{
    @Override
    public Users parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        Users users = new Gson().fromJson(string, Users.class);
        return users;
    }


}
