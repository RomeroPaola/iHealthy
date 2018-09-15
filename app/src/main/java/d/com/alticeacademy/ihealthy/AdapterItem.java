package d.com.alticeacademy.ihealthy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterItem  extends BaseAdapter{

    private Activity activity;
    private ArrayList<MenuItem> items ;

   public AdapterItem (Activity activity, ArrayList<MenuItem> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v= inf.inflate(R.layout.item, viewGroup, false);

        }

        MenuItem menu = items.get(i);

        TextView title = v.findViewById(R.id.list_row_title);
        title.setText(menu.getTitle());
        ImageView image =  v.findViewById(R.id.list_row_image);
        image.setImageDrawable(menu.getIcon());
        ImageView go =  v.findViewById(R.id.list_row_go);
        go.setImageDrawable(menu.getGo());

        return v;
    }


}
