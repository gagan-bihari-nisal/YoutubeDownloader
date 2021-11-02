package com.example.youtubedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {
    String downloadlink;
    String newLink;
    EditText vidurl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vidurl=(EditText)findViewById(R.id.eturl);

       if(getSupportActionBar()!=null){
           getSupportActionBar().hide();
       }
    }


    public void DownloadVideo(View view) {
        downloadlink=vidurl.getText().toString().trim();
        YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(MainActivity.this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                if(ytFiles!=null)
                {
                    int tag=18;
                    newLink=ytFiles.get(tag).getUrl();

                    DownloadManager.Request request=new DownloadManager.Request(Uri.parse(newLink));
                    request.setTitle(videoTitle+System.currentTimeMillis()+"video");
                    request.setDescription("Downloading File");
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,videoTitle+System.currentTimeMillis()+".mp4");
                    DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    downloadManager.enqueue(request);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Cannot process",Toast.LENGTH_LONG).show();
                }
            }
        };
        youTubeUriExtractor.execute(downloadlink);
    }

    public void DownloadAudio(View view) {
        downloadlink=vidurl.getText().toString().trim();
        YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(MainActivity.this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                if(ytFiles!=null)
                {
                    int tag=22;
                    newLink=ytFiles.get(tag).getUrl();

                    DownloadManager.Request request=new DownloadManager.Request(Uri.parse(newLink));
                    request.setTitle(videoTitle+System.currentTimeMillis()+".mp3");
                    request.setDescription("Downloading File");
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,videoTitle+System.currentTimeMillis()+".mp3");
                    DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    downloadManager.enqueue(request);
                }
            }
        };
        youTubeUriExtractor.execute(downloadlink);
    }
}