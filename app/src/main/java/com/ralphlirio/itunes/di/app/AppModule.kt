package com.ralphlirio.itunes.di.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions.placeholderOf(R.drawable.ic_icons8_itunes_100)
            .error(R.drawable.ic_error_image)


    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager =
        Glide.with(application).setDefaultRequestOptions(requestOptions)


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    /*
     * Builds an instance of the OkHttpClient that is cache-enabled
     * and can handle fetching the data efficiently when:
     * the device is offline, and
     * the device needs to access the same data from the network within a short span of time
     * @param application
     * @param hasNetwork if device has network connection
     * @return an instance of the OkHttpClient
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application, hasNetwork: Boolean): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(application.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                /*
                 * Initialize the request and change its header depending on whether
                 * the device is connected to Internet or not.
                 */
                request = if (hasNetwork)
                    /*
                     *  If there is Internet, get the cache that was stored within the last 5 seconds.
                     */
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                else
                    /*
                     *  If there is no Internet, get the cache that was within the last 7 days.
                     */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()
    }

    /*
     * @return boolean if device has network connection
     */
    @Singleton
    @Provides
    fun provideNetworkCheck(application: Application): Boolean {
        var isConnected: Boolean? = false
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected!!
    }

}
