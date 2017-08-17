package com.unibo.koci.moneytracking.Adapters;

/*
 * Created by koale on 12/08/17.
 */

import java.util.List;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibo.koci.moneytracking.R;

import static android.R.id.input;


public class MoneyItemAdapter extends RecyclerView.Adapter<MoneyItemAdapter.ViewHolder> {
    private List<String> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView iconitem;
        public ImageButton settingCard;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.itemlist_title);
            txtFooter = (TextView) v.findViewById(R.id.itemlist_description);
            iconitem = (ImageView) v.findViewById(R.id.itemlist_icon);
            settingCard = (ImageButton) v.findViewById(R.id.setting_card);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MoneyItemAdapter(List<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MoneyItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.money_item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final String name = values.get(holder.getAdapterPosition());



        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());
            }
        });

        holder.txtFooter.setText("Footer: " + name);

        if (values.get(holder.getAdapterPosition()).toLowerCase().contains("3")) {
            holder.iconitem.setImageResource(R.drawable.thumb_up);
        }
        else {
            holder.iconitem.setImageResource(R.drawable.thumb_down);

        }


        holder.settingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.settingCard,position);
            }
        });

    }

    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_card_option, popup.getMenu());
        //popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
/*
class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    private int position;
    public MyMenuItemClickListener(int positon) {
        this.position=positon;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.Not_interasted_catugury:
                String RemoveCategory=mDataSet.get(position).getCategory();
                // mDataSet.remove(position);
                //notifyItemRemoved(position);
                // notifyItemRangeChanged(position,mDataSet.size());

                mySharedPreferences.saveStringPrefs(Constants.REMOVE_CTAGURY,RemoveCategory,MainActivity.context);
                Toast.makeText(MainActivity.context, "Add to favourite", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.No_interasted:
                mDataSet.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mDataSet.size());
                Toast.makeText(MainActivity.context, "Done for now", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                mySharedPreferences.deletePrefs(Constants.REMOVE_CTAGURY,MainActivity.context);
            default:
        }
        return false;
    }
}
*/