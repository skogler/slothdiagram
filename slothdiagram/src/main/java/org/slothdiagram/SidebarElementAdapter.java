package org.slothdiagram;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SidebarElementAdapter extends BaseAdapter {

    private final Context context;
    private final List<DrawableElement> elements;

    public SidebarElementAdapter(Context context, List<DrawableElement> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public View getView(int position, View elementView, ViewGroup parent) {
        ImageView currentView;

        if (elementView == null) {

            currentView = new ImageView(context);

            DrawableElement el = elements.get(position);
            if (el instanceof PictureElement) {
                PictureElement pictureElement = (PictureElement) el;
                currentView.setImageDrawable(pictureElement.getDrawable());
            }

            // // TODO: Remove test code
            // if (position > 2 && position < 5) {
            // currentView.setImageDrawable(new BitmapDrawable(context
            // .getResources(), BitmapFactory.decodeResource(
            // context.getResources(), R.raw.mini_test_icon)));
            // } else {
            // currentView.setImageDrawable(new BitmapDrawable(context
            // .getResources(), BitmapFactory.decodeResource(
            // context.getResources(), R.raw.test)));
            // }
            // // end of testcode

        } else {
            currentView = (ImageView) elementView;
        }

        return currentView;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
