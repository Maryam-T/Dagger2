package com.marand.dagger3.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.marand.dagger3.R;
import com.marand.dagger3.model.User;
import com.marand.dagger3.viewmodel.ViewModelProviderFactory;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mText_status;
    private RecyclerView mRecycler_users;
    private MainViewModel mViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        initView();
        initUsersRecyclerView();
        subscribeObservers();
    }

// -------------------------------------------------------------------------------------------------

    private void initView() {
        mText_status = findViewById(R.id.txt_status);
        mRecycler_users = findViewById(R.id.recycler_users);
    }

    private void subscribeObservers() {
        mViewModel.getUsers().observe(this, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case SUCCESS: {
                            showTextView(false, "");
                            usersAdapter.setUsers(listResource.data);
                            break;
                        }
                        case ERROR: {
                            showTextView(true, listResource.message);
                            break;
                        }
                        case LOADING: {
                            showTextView(true, getString(R.string.loading));
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initUsersRecyclerView() {
        mRecycler_users.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_users.setAdapter(usersAdapter);
    }

    private void showTextView(boolean isVisible, String message) {
        if (isVisible) {
            mText_status.setVisibility(View.VISIBLE);
            mText_status.setText(message);
        } else {
            mText_status.setVisibility(View.GONE);
        }
    }
}
