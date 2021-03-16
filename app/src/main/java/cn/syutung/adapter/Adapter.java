package cn.syutung.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import cn.syutung.empty.Article;
import cn.syutung.myblogapp.R;

public class Adapter extends ArrayAdapter<Article> {
    private final int resourceId;

    public Adapter(Context context, int resourceId, ArrayList<Article> list) {
        super(context, resourceId,list);
        this.resourceId = resourceId;
    }

    @SuppressLint({"CutPasteId", "ResourceType"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Article article= (Article) getItem(position); //获取当前项的Fruit实例
        View view ;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.name=view.findViewById(R.id.titles2);
            viewHolder.text=view.findViewById(R.id.samtext2);
            viewHolder.imageView = view.findViewById(R.id.banner);
            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        // 获取控件实例，并调用set...方法使其显示出来
        viewHolder.name.setText(article.getName());
        viewHolder.text.setText(article.getText());
       // ReturnBitmap thread = new ReturnBitmap(article.getBanner());
       // thread.run();
        showImageByAsyncTask(viewHolder.imageView,article.getBanner());
        //viewHolder.imageView.setImageBitmap(getBitmap(article.getBanner()));

        return view;
    }

    //-----------------------AsyncTask异步访问图片-----------------------

    public void showImageByAsyncTask(ImageView imageView,String url){
        new NewsAsyncTask(imageView).execute(url);
    }

    public class NewsAsyncTask extends AsyncTask<String, Void, Bitmap>{

        private ImageView mImageView;

        public NewsAsyncTask(ImageView imageView){
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            return getBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // TODO Auto-generated method stub
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
        }
    }

    //------------------------------------------------------------------


    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
    static class ViewHolder{
        TextView name;//歌曲名
        TextView text;//歌手
        ImageView imageView;
    }
}
