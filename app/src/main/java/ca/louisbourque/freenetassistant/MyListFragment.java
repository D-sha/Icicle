package ca.louisbourque.freenetassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v4.app.ListFragment;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;

public class MyListFragment extends ListFragment {



    public interface OnItemSelectedListener {
        public void redrawNodeManagementActionBar();
    }
    private OnItemSelectedListener listener;
    private CopyOnWriteArrayList<LocalNode> values;
    private GlobalState gs;
    // This is the Adapter being used to display the list's data
    private NodeManagerArrayAdapter mAdapter;
    private ListView list;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.gs = (GlobalState) getActivity().getApplication();
        values = this.gs.getLocalNodeList();
        mAdapter = new NodeManagerArrayAdapter(getActivity(),values);

        this.list = getListView();


        setListAdapter(mAdapter);
        this.list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.list.setDivider(getResources().getDrawable(R.drawable.divider));
        this.list.setSelector(getResources().getDrawable(R.drawable.list_selection_background));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        l.setItemChecked(position,true);
        if(listener != null) {
            listener.redrawNodeManagementActionBar();
        }
    }

    private class NodeManagerArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private CopyOnWriteArrayList<LocalNode> values;

        public NodeManagerArrayAdapter(Context context, CopyOnWriteArrayList<LocalNode> values) {
            super(context, R.layout.peer);
            this.context = context;
            this.values = values;
        }

        public int getCount (){
            return values.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.peer, parent, false);
            TextView peerName = (TextView) rowView.findViewById(R.id.peer_name);
            TextView peerAddress = (TextView) rowView.findViewById(R.id.peer_address);
            peerName.setText(values.get(position).getName());
            peerAddress.setText(values.get(position).getAddress()+":"+values.get(position).getPort());
            ImageView peerStatus = (ImageView)rowView.findViewById(R.id.peer_icon);
            if(gs.getActiveLocalNodeIndex() == position){
                peerStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white_36dp));
                peerStatus.setBackground(getResources().getDrawable(R.drawable.round_button_green));
                peerStatus.setVisibility(View.VISIBLE);
            }else{
                peerStatus.setVisibility(View.INVISIBLE);
            }

            return rowView;
        }
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public CopyOnWriteArrayList<LocalNode> getValues() {
        return values;
    }
}