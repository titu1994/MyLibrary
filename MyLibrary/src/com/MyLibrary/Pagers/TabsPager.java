package com.MyLibrary.Pagers;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class TabsPager extends FragmentStatePagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
	private final Context mContext;
    private final ActionBar mActionBar;
    private final ViewPager mViewPager;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private DisplayMetrics mDisplayMetrics;
    
    private TitlePageIndicator titlePageIndicator = null;
    private TabPageIndicator tabPageIndicator = null;
    private CirclePageIndicator circlePageIndicator = null;
    
    private float mScaledDensity;
    private boolean isInTabMode = false;
    
    public static final String ROBOTO_FONT_ASSET_PATH = "fonts/Roboto-Light.ttf";
    
    /**
     * @author Yue A
     *	
     * To add any of the indicators to the tabs pager, do this.
     * Add 	<com.viewpagerindicator.TitlePageIndicator
			android:id="@+id/titles"
			android:layout_height="wrap_content"
			android:layout_width="fill_parent" />
			
			right before the ViewPager in the xml layout file of the activity where it's being used.
			
			<android.support.v4.view.ViewPager
        	android:id="@+id/MainViewPager"
        	android:layout_width="fill_parent"
        	android:layout_height="0dp"
        	android:layout_weight="1" />
        
			Then initialise it using the code after initialising and setting the adapter of the ViewPager.
			
			TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
			titleIndicator.setViewPager(pager);
			
			in the activity and pass it onto the TabsPager class.
     */
	
    static final class TabInfo {
        private final Class<?> clss;
        private final Bundle args;
        private final String text;

        TabInfo(Class<?> _class, Bundle _args, String _text) {
            clss = _class;
            args = _args;
            text = _text;
        }
    }
    
	public TabsPager(SherlockFragmentActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getSupportActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
        
        isInTabMode = true;
    }
	
	public TabsPager(SherlockFragmentActivity activity, ViewPager pager, TitlePageIndicator pageIndicator) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getSupportActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        titlePageIndicator = pageIndicator;
        titlePageIndicator.setViewPager(mViewPager);
        titlePageIndicator.setOnPageChangeListener(this);
        
        mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        mScaledDensity = mDisplayMetrics.scaledDensity;
        mDisplayMetrics = null;
        
        isInTabMode = false;
    }
	
	public TabsPager(SherlockFragmentActivity activity, ViewPager pager, TabPageIndicator pageIndicator) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getSupportActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        tabPageIndicator = pageIndicator;
        tabPageIndicator.setViewPager(mViewPager);
        tabPageIndicator.setOnPageChangeListener(this);
        
        isInTabMode = false;
    }
	
	public TabsPager(SherlockFragmentActivity activity, ViewPager pager, CirclePageIndicator pageIndicator) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mActionBar = activity.getSupportActionBar();
        mViewPager = pager;
        mViewPager.setAdapter(this);
        circlePageIndicator = pageIndicator;
        circlePageIndicator.setViewPager(mViewPager);
        circlePageIndicator.setOnPageChangeListener(this);
        
        isInTabMode = false;
    }
	
	public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
		String text = (String) tab.getText();
        TabInfo info = new TabInfo(clss, args, text);
        tab.setTag(info);
        tab.setTabListener(this);
        mTabs.add(info);
        mActionBar.addTab(tab);
        notifyDataSetChanged();
    }


	@Override
	public int getCount() {
		return mTabs.size();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		if(isInTabMode){
			mActionBar.setSelectedNavigationItem(position);
		}
		else{
			mViewPager.setCurrentItem(position);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Object tag = tab.getTag();
		int size = mTabs.size();
        for (int i=0; i<size; i++) {
            if (mTabs.get(i) == tag) {
                mViewPager.setCurrentItem(i);
            }
        }
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return mTabs.get(position).text;
	}

	@Override
	public Fragment getItem(int position) {
		TabInfo info = mTabs.get(position);
		return Fragment.instantiate(mContext, info.clss.getName(), info.args);
	}

	/**
	 * Used to change the Typeface of any ViewPagerIndicator currently in use. You must use the TitlePageIndicator to use this method.
	 * @param fontAssetPath = Set as "fonts/Roboto-Light.ttf" for use in the assets folder. 
	 * 						  If a null value is passed then doesn't change the font which makes the method call redundant.
	 */
	public void setTypefaceOfTitlePageIndicator(String fontAssetPath){
		if(fontAssetPath != null){
			if(titlePageIndicator != null){
				titlePageIndicator.setTypeface(Typeface.createFromAsset(mContext.getAssets(), fontAssetPath));
			}
		}
	}
	
	/**
	 * Used to change the text size of the title page indicator. 
	 * @param textSize = Text size in float. It will be scaled according to "sp" format.
	 */
	public void setTextSizeOfTitlePageIndicator(float textSize){
		if(titlePageIndicator != null){
			titlePageIndicator.setTextSize(textSize * mScaledDensity);
		}
	}
}

/*
 * public class ActionBarTabsPager extends Activity {
    ViewPager mViewPager;
    TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText("Simple"),
                CountingFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("List"),
                FragmentPagerSupport.ArrayListFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Cursor"),
                CursorFragment.class, null);

        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
 * 
 */
