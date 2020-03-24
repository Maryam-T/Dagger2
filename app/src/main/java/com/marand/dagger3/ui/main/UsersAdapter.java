package com.marand.dagger3.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.marand.dagger3.R;
import com.marand.dagger3.model.User;
import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView mText_data;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mText_data = itemView.findViewById(R.id.txt_data);
        }

        public void bind(User user){
            mText_data.setText(user.getUsername()+
                    "\n"+
                    user.getEmail());
        }
    }

// -------------------------------------------------------------------------------------------------

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
}
