package com.app.todo.retrofit;

import java.lang.ref.WeakReference;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohitesh on 03/11/16.
 */
public class ApiController<T> {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    public void hitApi(final RequestBuilder requestBuilder, final ApiDataReceiveCallback callback) {
        Call<String> call = getApiFromApiType(requestBuilder.apiType,requestBuilder.getExtraParameters());
        if(call != null) {
            call.enqueue(new ApiResponseCallBack(callback,requestBuilder.apiType));
        }
    }

    private Call<String> getApiFromApiType(int apiType, Map<String, String> extraParams) {
        switch (apiType) {

            default:
                return apiService.fetchAllTasks();
        }

    }

    private static class ApiResponseCallBack implements Callback<String> {

        private final WeakReference<ApiDataReceiveCallback> apiDataReceiveCallbackWeakReference;
        private int type;

        ApiResponseCallBack(ApiDataReceiveCallback apiDataReceiveCallback, int type) {
            this.apiDataReceiveCallbackWeakReference = new WeakReference<>(apiDataReceiveCallback);
            this.type = type;
        }

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            try {
                if(response.isSuccessful()) {
                    handleApiResponse(response.body());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleApiResponse(final String response) throws Exception {
            ApiDataReceiveCallback apiDataReceiveCallback = apiDataReceiveCallbackWeakReference.get();
            if (apiDataReceiveCallback != null)
                apiDataReceiveCallback.onDataReceived(response, type);
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            t.printStackTrace();
        }
    }
}
