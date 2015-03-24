package toidiu.co.swipemaven;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by toidiu on 3/23/15.
 */
public class MainFragment extends android.support.v4.app.Fragment
{

    public static android.support.v4.app.Fragment newInstance(String s)
    {
        MainFragment detailFragment = new MainFragment();
        return detailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        ImageView oysterImage = (ImageView) rootView.findViewById(R.id.oyster_image);
        final ProgressBar progress = (ProgressBar) rootView.findViewById(R.id.progress);


        Window window = getActivity().getWindow();
        int height = window.getDecorView().getBottom();
        int width = window.getDecorView().getRight();
        double ratio = (double) height / (double) width;
        double hOverW = 1.775; // 1136/640
        //fixme do some optimization
        /*
        if
         */

        Picasso.with(getActivity()).load(R.mipmap.ic_launcher).resize(640, 1136).centerCrop()
                .config(Bitmap.Config.RGB_565).into(oysterImage, new Callback()
        {
            @Override
            public void onSuccess()
            {
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onError()
            {

            }

        });


        return rootView;
    }
}
