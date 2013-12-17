package com.tokbox.opentok.phonegap;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.opentok.android.Connection;
import com.opentok.android.OpentokException;
import com.opentok.android.Publisher;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

public class OpenTokAndroidPlugin extends CordovaPlugin implements Session.Listener, Publisher.Listener, Subscriber.Listener {
  protected Session mSession;
  protected Publisher mPublisher;
  public static final String TAG = "OTPlugin";

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    Log.i( TAG, action );
    mSession = Session.newInstance( this.cordova.getActivity().getApplicationContext() , "2_MX40NDQ0MzEyMn5-TW9uIERlYyAxNiAxMjowOTo1NyBQU1QgMjAxM34wLjg3OTY3NzV-", this );
    mSession.connect("44443122", "T1==cGFydG5lcl9pZD00NDQ0MzEyMiZzaWc9NmM2YmU5MjMwYTNhZmM0NzRlOTFkYjViYjAzYTIxNWU2ZjIyNWI3MDpjcmVhdGVfdGltZT0xMzg3MjI0NTk3Jm5vbmNlPTAuMDQ3NzgyNDY0NDU2MTUyOTcmcm9sZT1wdWJsaXNoZXImc2Vzc2lvbl9pZD0yX01YNDBORFEwTXpFeU1uNS1UVzl1SUVSbFl5QXhOaUF4TWpvd09UbzFOeUJRVTFRZ01qQXhNMzR3TGpnM09UWTNOelYt");
    callbackContext.success();
    return true;
  }

   @Override
    public void onSessionConnected() {
        Log.i(TAG, "session connected.");

    }
    public void onSessionDisconnected() {
        Log.i(TAG, "session disconnected.");
    }

    @Override
    public void onSessionException(OpentokException e) {
        Log.e(TAG, "session exception: " + e.getMessage());
    }

    @Override
    public void onSubscriberException(Subscriber subscriber, OpentokException e) {
        Log.e(TAG, "subscriber exception: " + e.getMessage() + ", stream id: " + subscriber.getStream().getStreamId());
    }

    @Override
    public void onSubscriberVideoDisabled(Subscriber subscriber) {
        Log.i(TAG, "subscriber video disabled, stream id: " + subscriber.getStream().getStreamId());
    }


    @Override
    public void onSessionCreatedConnection(Connection connection) {
        Log.i(TAG, "connection created: " + connection.getConnectionId());
    }

    @Override
    public void onSessionDroppedConnection(Connection connection) {
        Log.i(TAG, "connection dropped: " + connection.getConnectionId());
    }


    @Override
    public void onPublisherStreamingStarted() {

    }

    @Override
    public void onPublisherStreamingStopped() {
        Log.i( TAG, "publisher is not streaming.");
    }

    @Override
    public void onPublisherChangedCamera(int i) {

    }

    @Override
    public void onPublisherException(OpentokException e) {

    }

    @Override
    public void onSubscriberConnected(Subscriber subscriber) {

    }

    @Override
    public void onSessionReceivedStream(Stream stream) {
    }

    @Override
    public void onSessionDroppedStream(Stream stream) {
    }
}

