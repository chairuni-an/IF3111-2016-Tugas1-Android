package com.example.chairuniaulianusapati.itb_race;

/**
 * Created by chairuniaulianusapati on 3/25/16.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Context context;

    Client(String addr, int port, TextView textResponse, Context context) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
        this.context = context.getApplicationContext();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Socket socket = null;
        PrintWriter out;
        BufferedReader in;
        try {
            JSONObject json = new JSONObject();
            json.put("com", "req_loc");
            json.put("nim", "13513054");
            socket = new Socket(dstAddress, dstPort);
            Log.i("Client", "Connecting...");
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(json.toString());
            Log.i("Client", "Request Message: " + json.toString());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int c;
            String responseString = "";
            while((c=in.read())!=-1){
                responseString+=(char)c;
            }
            Log.i("Client", "Response: " + responseString);
         /*
          * notice: inputStream.read() will block if no data return
          */
        }catch(org.json.JSONException e){
            // nothing
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
        Intent intent = new Intent(context, MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
