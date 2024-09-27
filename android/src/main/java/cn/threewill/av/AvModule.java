package cn.threewill.av;


import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.ExecuteCallback;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.arthenica.mobileffmpeg.LogCallback;
import com.arthenica.mobileffmpeg.LogMessage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class AvModule extends ReactContextBaseJavaModule{

  public AvModule(@Nullable ReactApplicationContext context) {
    super(context);
  }
  @NonNull
  @Override
  public String getName() {
    return "TwAv";
  }

  /**
   * 转换文件
   * @param sourceUri
   * @param targetUri
   */
  @ReactMethod
  public void convertFile(String sourceUri, String  targetUri, Promise promise){
    try {
      File source = new File(new URI(sourceUri));
      File target = new File(new URI(targetUri));
      FFmpeg.executeAsync(new String[]{"-y", "-i", source.getAbsolutePath(), target.getAbsolutePath()}, new ExecuteCallback() {
        @Override
        public void apply(long executionId, int returnCode) {
          if (returnCode == Config.RETURN_CODE_SUCCESS) {
            WritableMap map = Arguments.createMap();
            map.putString("uri",targetUri);
            map.putInt("size", (int)(target.length()*0.001));
            map.putString("name", target.getName());
            promise.resolve(map);
          } else {
            promise.reject(new Exception("convert failed"));
          }
        }
      });
    } catch (URISyntaxException e) {
      e.printStackTrace();
      promise.reject(e);
    }
  }



  @ReactMethod
  public void cancelConvert(){
    FFmpeg.cancel();
  }

  /**
   * 日志
   */
  @ReactMethod
  public void enableLog(Callback callback){
    if (callback == null){
      Config.enableLogCallback(null);
    }else {
      Config.enableLogCallback(new LogCallback() {
        @Override
        public void apply(LogMessage message) {
          WritableMap map = Arguments.createMap();
          map.putString("message", message.getText());
          callback.invoke(map);
        }
      });
    }
  }



}


