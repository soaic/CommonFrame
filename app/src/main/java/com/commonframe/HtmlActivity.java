package com.commonframe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.commonframe.base.WebBrowserActivity;

public class HtmlActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebBrowserActivity.class);
                intent.putExtra(WebBrowserActivity.INTENT_URL,"file:///android_asset/html1/index.html");
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebBrowserActivity.class);
                intent.putExtra(WebBrowserActivity.INTENT_URL,"file:///android_asset/html2/index.html");
                startActivity(intent);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebBrowserActivity.class);
                intent.putExtra(WebBrowserActivity.INTENT_URL,"file:///android_asset/html3/index.html");
                startActivity(intent);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebBrowserActivity.class);
                intent.putExtra(WebBrowserActivity.INTENT_URL,"file:///android_asset/html4/index.html");
                startActivity(intent);
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),WebBrowserActivity.class);
                intent.putExtra(WebBrowserActivity.INTENT_URL,"file:///android_asset/html5/index.html");
                startActivity(intent);
            }
        });
    }
}
