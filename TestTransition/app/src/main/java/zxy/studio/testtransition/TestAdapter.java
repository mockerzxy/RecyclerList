package zxy.studio.testtransition;

import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xueyuanzhang on 17/9/12.
 */

public class TestAdapter extends RecyclerView.Adapter {
    private List<Note> list;
    public static final int TYPE_EXPAND = 2;
    public static final int TYPE_FOLD = 3;
    public RecyclerView mRecyclerView;

    public TestAdapter(List<Note> list, RecyclerView view) {
        this.list = list;
        mRecyclerView = view;
    }

    @Override
    public int getItemViewType(int position) {
        Note entity = list.get(position);
        if (entity.isExpand) {
            return TYPE_EXPAND;
        } else {
            return TYPE_FOLD;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TestHolder mHolder = (TestHolder) holder;
        Note note = list.get(position);
        mHolder.textView.setText(note.foldText);
        if (mHolder.expandHolder != null) {
            mHolder.expandHolder.editText.setText(note.expandText);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ExpandHolder expandHolder;
        public Scene orignalScene;
        public Scene expandScene;
        public LinearLayout viewRoot;

        public TestHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
            viewRoot = (LinearLayout) itemView.findViewById(R.id.viewroot);
            orignalScene = new Scene(viewRoot, viewRoot.findViewById(R.id.textview));
            expandScene = Scene.getSceneForLayout(viewRoot, R.layout.expand, itemView.getContext());
            itemView.setOnClickListener(this);
            viewRoot.setOnClickListener(this);
        }

        public void expand() {
            TransitionManager.go(expandScene);
            if (expandHolder == null) {
                expandHolder = new ExpandHolder(viewRoot);
            }
            expandHolder.editText.setText(list.get(getPosition()).expandText);
        }

        public void fold() {
            TransitionManager.go(orignalScene);
            textView.setText(list.get(getPosition()).foldText);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.viewroot) {
//                for (int i = 0; i < getItemCount(); i++) {
//                    TestHolder holder = (TestHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getLayoutManager().getChildAt(i));
//                    if (holder != null) {
//                        holder.fold();
//                    }
//                }
                expand();
            }
        }

        public class ExpandHolder {
            public EditText editText;

            public ExpandHolder(View itemView) {
                editText = (EditText) itemView.findViewById(R.id.edittext);
            }
        }
    }


}
