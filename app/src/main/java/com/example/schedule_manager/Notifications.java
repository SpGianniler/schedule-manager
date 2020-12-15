/*package com.example.schedule_manager;

import android.os.Message;
import android.util.Log;

import com.android.volley.toolbox.HttpResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

public class Notifications {

    public Notifications() throws IOException {
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        String apiKey = "eNk8C-ZS2MA:APA91bGjrlAPg-U3Lz48-PXo_TBn6rrgJqHbXhAIj0cOWNNLYZUs0tmbEzqZ8kqRQ3XiuG_mBWApyfk63Sm5b235l23p7kyCIalI4QTV7L-P7bbmAMFbUEtliyRb8sAaawGc3L5QFHJS";

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");

        String topic = "highScores";

// See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setTopic(topic)
                .build();

// Send a message to the devices subscribed to the provided topic.
        String response = FirebaseMessaging.getInstance().send(message);
    }
}*/
// print result



/* System.out.println(response.toString());

        String jsonInputString = "{\n" +
                "  \"to\": \"/topics/Programma\",\n" +
                "  \"data\": {\n" +
                "    \"message\": \"This is a Firebase Cloud Messaging Topic Message!\",\n" +
                "   }\n" +
                "}\n" +
                "\n";
        }*/



