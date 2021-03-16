package cn.syutung.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.syutung.myblogapp.R;

public class WebAdapter extends BaseAdapter {
    private String htmls = "";
    private Context context;
    private int resourceId;
    public WebAdapter(Context context, int resourceId, String htmls){
        this.context=context;
        this.resourceId=resourceId;
        this.htmls = htmls;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.webView = view.findViewById(R.id.webq);
            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.webView.loadData(Html.fromHtml("<html>"+htmls+"</html>").toString(), "text/html", "UTF-8");
        //viewHolder.imageView.setImageBitmap(getBitmap(article.getBanner()));

        return view;
    }
    static class ViewHolder{
        WebView webView;
    }
}
