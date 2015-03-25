package toidiu.co.swipemaven;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import toidiu.co.swipemaven.views.LockableScrollView;


public class MainActivity extends ActionBarActivity
{
    //~=~=~=~=~=~=~=~=~=~=~=~=Views
    private LockableScrollView lockableScroll;
    private ViewPager          swipe;
    private View               scrollPadding;

    //~=~=~=~=~=~=~=~=~=~=~=~=Fields
    Handler handler = new Handler();
    Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            Log.d("------runnable", "unlock");
            lockableScroll.setScrollingEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (ViewPager) findViewById(R.id.pager);
        lockableScroll = (LockableScrollView) findViewById(R.id.lock_scroll);
        scrollPadding = findViewById(R.id.scroll_padding);

        initAdapter();
        initScrollViewOverlay();
    }

    private void initAdapter()
    {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), list);
        swipe.setAdapter(adapter);
    }

    public int getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0)
        {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initScrollViewOverlay()
    {
        final View layout = findViewById(R.id.detail_layout);
        layout.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        int screenHeight = layout.getMeasuredHeight();
                        int statusBarHeight = getStatusBarHeight();
                        int nameTextHeight = findViewById(R.id.title).getMeasuredHeight();
                        int extraHeight = 200;

                        int padding = screenHeight - statusBarHeight - nameTextHeight - extraHeight;
                        scrollPadding.setPadding(0, padding, 0, 0);
                    }
                });

        scrollPadding.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Log.d("------touch", "lock");
                lockableScroll.setScrollingEnabled(false);
                return swipe.onTouchEvent(event);
            }
        });

        swipe.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                Log.d("------onPageScrolled", "now");
            }

            @Override
            public void onPageSelected(int position)
            {
                Log.d("------onPageSelected", "now");
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
                Log.d("------onPageScrollStateChanged", String.valueOf(state));
                if(state == ViewPager.SCROLL_STATE_IDLE)
                {
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, 800);
                }
            }
        });
    }
}
