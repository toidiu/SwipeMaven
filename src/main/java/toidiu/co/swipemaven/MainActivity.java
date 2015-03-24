package toidiu.co.swipemaven;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        List<String> list = new ArrayList<>();
        MainAdapter  adapter = new MainAdapter(getSupportFragmentManager(),
                                               list);
        pager.setAdapter(adapter);

    }



}
