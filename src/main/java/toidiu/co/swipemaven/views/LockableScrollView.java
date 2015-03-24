package toidiu.co.swipemaven.views;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by toidiu on 2/18/15.
 */
public class LockableScrollView extends ScrollView
{

    private Callbacks mCallbacks;
    PointF start = new PointF();

    private final int MOVE_THRESHOLD = 100;

    // true if we can scroll (not locked)
    // false if we cannot scroll (locked)
    private boolean mScrollable = true;

    public LockableScrollView(Context context)
    {
        super(context);
    }

    public LockableScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public LockableScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void setScrollingEnabled(boolean enabled)
    {
        mScrollable = enabled;
    }

    public boolean isScrollable()
    {
        return mScrollable;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();
        if(action == MotionEvent.ACTION_DOWN)
        {
            start.set(ev.getX(), ev.getY());
            // if we can scroll pass the event to the superclass
            if(mScrollable)
            {
                return super.onTouchEvent(ev);
            }
        }
        else if(action == MotionEvent.ACTION_MOVE)
        {
//            double move_down = Math.abs(start.x) - Math.abs(ev.getX());
            double move_left = Math.abs(start.y) - Math.abs(ev.getY());
            if((move_left < - MOVE_THRESHOLD))
            {
                mScrollable = false;
            }
//            if((move_down < - MOVE_THRESHOLD))
//            {
//                // MOVE DOWN
//            }
//            else if((move_down > MOVE_THRESHOLD))
//            {
//                // MOVE UP
//            }
        }
        else if(action == MotionEvent.ACTION_UP)
        {
            setScrollingEnabled(true);
        }


        if(mCallbacks != null)
        {
            switch(ev.getActionMasked())
            {
                case MotionEvent.ACTION_DOWN:
                    mCallbacks.onDownMotionEvent();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mCallbacks.onUpOrCancelMotionEvent();
                    break;
            }
        }



        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        // Don't do anything with intercepted touch events if
        // we are not scrollable
        if(! mScrollable)
        {
            return false;
        }
        else
        {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mCallbacks != null)
        {
            mCallbacks.onScrollChanged(t);
        }
    }

    @Override
    public int computeVerticalScrollRange()
    {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(Callbacks listener)
    {
        mCallbacks = listener;
    }

    public static interface Callbacks
    {
        public void onScrollChanged(int scrollY);

        public void onDownMotionEvent();

        public void onUpOrCancelMotionEvent();
    }


}