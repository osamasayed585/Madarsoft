package com.madarsoft.madarsoft.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madarsoft.madarsoft.R;
import com.madarsoft.madarsoft.classes.datebase.entities.User;
import com.madarsoft.madarsoft.databinding.RowUserItemBinding;

import java.util.List;

import javax.inject.Inject;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {


    private List<User> mData;
    private CouponsAdapterOnClickHandler mClickHandler;
    Context context;

    @Inject
    public UsersAdapter() {
    }

    public void initItemClickListener(CouponsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mData.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }

    public void setData(List<User> data) {
        mData = data;
        notifyDataSetChanged();
    }

    //The interface that will be implemented later in parent activity
    public interface CouponsAdapterOnClickHandler {
        void onCouponItemClick(User coupon);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //get an Instance for dataBinding
        private RowUserItemBinding mBinding;
        private User currentUser;
        private int currentPosition;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = RowUserItemBinding.bind(itemView);
            itemView.setOnClickListener(this::onItemClick);
        }

        public void onItemClick(View view) {
            int adapterPosition = getAdapterPosition();
            //Data passed when clicked
            User coupon = mData.get(adapterPosition);
            if (mClickHandler != null)
                mClickHandler.onCouponItemClick(coupon);
        }

        public void bind(User user, int position) {

            mBinding.txvUserName.setText(user.getName());
            mBinding.txvJobTitle.setText(user.getJonTitle());
            mBinding.txvUserAge.setText(String.format("%s %s", user.getAge(), context.getResources().getString(R.string.years_old)));
            mBinding.txvUserGender.setText(user.getGender());

            currentUser = user;
            currentPosition = position;
        }
    }
}