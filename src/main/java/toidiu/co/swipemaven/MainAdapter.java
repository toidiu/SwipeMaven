package toidiu.co.swipemaven;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by toidiu on 3/23/15.
 */
public class MainAdapter extends FragmentPagerAdapter
{
    private final List<String> list;

    public MainAdapter(FragmentManager supportFragmentManager, List<String> list)
    {
        super(supportFragmentManager);
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Fragment getItem(int position)
    {
        return MainFragment.newInstance(list.get(position));
    }

}
