package com.kinstalk.android.demo.demotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.kinstalk.android.demo.delegate.OnFragmentInteractionListener;
import com.kinstalk.android.demo.fragment.CanvasFragment;
import com.kinstalk.android.demo.fragment.EditTextFragment;
import com.kinstalk.android.demo.fragment.HomeFragment;
import com.kinstalk.android.demo.fragment.SlideFragment;
import com.kinstalk.android.demo.fragment.TestFragmentDialog;
import com.kinstalk.android.demo.fragment.TextComputeFragment;
import com.kinstalk.android.model.model.BaseModel;
import com.kinstalk.android.model.model.VersionBean;
import com.kinstalk.android.model.service.CheckVersionService;
import com.kinstalk.android.model.utils.ComApi;
import com.kinstalk.android.model.utils.RequestUtils;
import com.kinstalk.android.model.utils.delegate.SimpleRequestCallback;
import com.siqing.demotest.R;

import java.lang.reflect.Field;

import io.reactivex.disposables.Disposable;

public class DemoActivity extends BaseActivity implements OnFragmentInteractionListener {
    public String TAG = "DemoActivity";
    private FrameLayout containerBox;
    private Fragment mLastFragment;
    private Fragment mCurrFragment;
    private ActionBar actionBar;

    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    MenuPopupHelper menuPopupHelper = (MenuPopupHelper) field.get(popupMenu);
//                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
//                            .getClass().getName());
//                    Method setForceIcons = classPopupHelper.getMethod(
//                            "setForceShowIcon", boolean.class);
//                    setForceIcons.invoke(menuPopupHelper, true);
                    menuPopupHelper.setForceShowIcon(true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        containerBox = (FrameLayout) findViewById(R.id.content);
        HomeFragment homeFragment = HomeFragment.newInstance(DemoActivity.class.getSimpleName(), DemoActivity.class.getSimpleName());
        addFragment(homeFragment);

        actionBar = getSupportActionBar();


//        Intent intent = new Intent(this, TranslateService.class);
//        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        if (id.equals("1")) {
            TextComputeFragment textComputeFragment = TextComputeFragment.newInstance(DemoActivity.class.getSimpleName());
            addFragment(textComputeFragment);
        } else if (id.equals("2")) {
            SlideFragment slideFragment = new SlideFragment();
            addFragment(slideFragment);
        } else if (id.equals("3")) {
            TestFragmentDialog.newInstance(getSupportFragmentManager());
        } else if (id.equals("4")) {
            CanvasFragment canvasFragment = new CanvasFragment();
            addFragment(canvasFragment);
        } else if (id.equals("5")) {
            EditTextFragment editTextFragment = new EditTextFragment();
            addFragment(editTextFragment);
        } else if (id.equals("6")) {
            checkVersion();
        }
    }

    @Override
    public void onFragmentClose(Fragment fragment) {
        removeFragment(fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        removeFragment(mCurrFragment);
        return true;
    }

    private void removeFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        getSupportFragmentManager().popBackStack();
        FragmentTransaction ft = getTransaction();
        if (mLastFragment != null) {
            ft.show(mLastFragment);
            mCurrFragment = mLastFragment;
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void addFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = getTransaction();
        if (mCurrFragment != null) {
//            ft.hide(mCurrFragment);
            mLastFragment = mCurrFragment;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ft.add(containerBox.getId(), fragment);
        ft.addToBackStack(null);
        ft.commit();
        mCurrFragment = fragment;
    }

    private FragmentTransaction getTransaction() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        return fragmentTransaction;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }
        removeFragment(getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size() - 1));
    }

    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v, Gravity.RIGHT);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, popupMenu.getMenu());
        setForceShowIcon(popupMenu);
        popupMenu.show();
    }

    private void checkVersion(){
        CheckVersionService service = ComApi.getInstance().getCheckVersionService();
        Disposable disposable = RequestUtils.excute(service.checkVersion(), new SimpleRequestCallback<BaseModel<VersionBean>>() {
            @Override
            public void onResult(BaseModel<VersionBean> t) {
                Log.e(TAG, t.toString());
            }

            @Override
            public void onComplete() {
            }

        });
        disposables.add(disposable);
    }
}
