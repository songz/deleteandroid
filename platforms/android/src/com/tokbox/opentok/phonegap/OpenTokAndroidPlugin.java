package com.tokbox.opentok.phonegap;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
  
  static JSONObject viewList = new JSONObject();
  static CordovaInterface _cordova;
  static CordovaWebView _webView;
  
  public class ListenPublisher implements Publisher.Listener{

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

  }
  
  public class RunnablePublisher extends ListenPublisher implements Runnable{
      @Override
      public void run() {
    	  ViewGroup frame = (ViewGroup) cordova.getActivity().findViewById(android.R.id.content);
		  mPublisher = Publisher.newInstance(cordova.getActivity().getApplicationContext(), this, null);
		  frame.addView(mPublisher.getView());
		  mSession.publish(mPublisher);
      }
  }
  
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
      _cordova = cordova;
      _webView = webView;
      Log.d(TAG, "Initialize Plugin");
      // By default, get a pointer to mainView and add mainView to the viewList as it always exists (hold phonegap's view)
      if (!viewList.has("mainView")) {
          // Cordova view is not in the viewList so add it.
          try {
              viewList.put("mainView", webView);
              Log.d(TAG, "Found CordovaView ****** " + webView);
          } catch (JSONException e) {
              // Error handle (this should never happen!)
              Log.e(TAG, "Critical error. Failed to retrieve Cordova's view");
          }
      }
      super.initialize(cordova, webView);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    Log.i( TAG, action );
    mSession = Session.newInstance( this.cordova.getActivity().getApplicationContext() , "2_MX40NDQ0MzEyMn5-VGh1IERlYyAxOSAxMjozNzoxOCBQU1QgMjAxM34wLjU4NjAyMzQ1fg", this );
    mSession.connect("44443122", "T1==cGFydG5lcl9pZD00NDQ0MzEyMiZzaWc9NTM1M2M1ZjY0YmZiYzE0OTY5MDE3NzNhMGYyNzRkOWM2MjE5ZDQyMTpzZXNzaW9uX2lkPTJfTVg0ME5EUTBNekV5TW41LVZHaDFJRVJsWXlBeE9TQXhNam96TnpveE9DQlFVMVFnTWpBeE0zNHdMalU0TmpBeU16UTFmZyZjcmVhdGVfdGltZT0xMzg3NDk3MTc2Jm5vbmNlPTc5MDc2OCZyb2xlPW1vZGVyYXRvcg==");
    
    callbackContext.success();
    return true;
  }

  public void alertUser( String message){
	// 1. Instantiate an AlertDialog.Builder with its constructor
	  AlertDialog.Builder builder = new AlertDialog.Builder( cordova.getActivity());
	  builder.setMessage( message ).setTitle( "TokBox Message");
	  AlertDialog dialog = builder.create();
  }
  
  
   @Override
    public void onSessionConnected() {
        Log.i(TAG, "session connected.");
        alertUser( "sessionConnectid!" );

        // Adding views
        cordova.getActivity().runOnUiThread( new RunnablePublisher() );
    }
    public void onSessionDisconnected() {
        Log.i(TAG, "session disconnected.");
    }

    @Override
    public void onSessionException(OpentokException e) {
        Log.e(TAG, "session exception: " + e.getMessage());
        alertUser("session error "+e.getMessage());
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

