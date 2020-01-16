package io.github.zileyuan.umeng_analytics_push;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import com.umeng.message.UTrack;
import android.util.Log;

/** UmengAnalyticsPushPlugin */
public class UmengAnalyticsPushPlugin implements MethodCallHandler {
  private Registrar registrar;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "umeng_analytics_push");
    channel.setMethodCallHandler(new UmengAnalyticsPushPlugin(registrar));
  }

  private UmengAnalyticsPushPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("addAlias")) {
      addAlias(call, result);
    } else if (call.method.equals("setAlias")) {
      setAlias(call, result);
    } else if (call.method.equals("deleteAlias")) {
      deleteAlias(call, result);
    } else {
      result.notImplemented();
    }
  }

  private void addAlias(MethodCall call, Result result) {
    String alias = call.argument("alias");
    String type = call.argument("type");
    if (UmengAnalyticsPushFlutterAndroid.UmengPushAgent != null) {
      UmengAnalyticsPushFlutterAndroid.UmengPushAgent.addAlias(alias, type, new UTrack.ICallBack() {
        @Override
        public void onMessage(boolean isSuccess, String message) {
          Log.i("umeng_push_alias", "addAlias：--> " + String.valueOf(isSuccess) + "; 消息：--> " + message);
        }
      });
    }
  }

  private void setAlias(MethodCall call, Result result) {
    String alias = call.argument("alias");
    String type = call.argument("type");
    if (UmengAnalyticsPushFlutterAndroid.UmengPushAgent != null) {
      UmengAnalyticsPushFlutterAndroid.UmengPushAgent.setAlias(alias, type, new UTrack.ICallBack() {
        @Override
        public void onMessage(boolean isSuccess, String message) {
          Log.i("umeng_push_alias", "setAlias：--> " + String.valueOf(isSuccess) + "; 消息：--> " + message);
        }
      });
    }
  }

  private void deleteAlias(MethodCall call, Result result) {
    String alias = call.argument("alias");
    String type = call.argument("type");
    if (UmengAnalyticsPushFlutterAndroid.UmengPushAgent != null) {
      UmengAnalyticsPushFlutterAndroid.UmengPushAgent.deleteAlias(alias, type, new UTrack.ICallBack() {
        @Override
        public void onMessage(boolean isSuccess, String message) {
          Log.i("umeng_push_alias", "deleteAlias：--> " + String.valueOf(isSuccess) + "; 消息：--> " + message);
        }
      });
    }
  }
}
