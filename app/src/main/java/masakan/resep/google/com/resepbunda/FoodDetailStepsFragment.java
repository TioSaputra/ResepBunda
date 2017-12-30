package masakan.resep.google.com.resepbunda;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tio on 12/29/17.
 */

public class FoodDetailStepsFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView lvSteps;
    ArrayList<String> steps;
    public FoodDetailStepsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.food_detail_steps_fragment, container, false);
    }

    public void fillList(){
        lvSteps = (ListView) getView().findViewById(R.id.lvsteps);
        CustomadapterStep customadapter = new CustomadapterStep();
        lvSteps.setAdapter(customadapter);
        lvSteps.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
        fillList();
    }

    class CustomadapterStep extends BaseAdapter {

        @Override
        public int getCount() {
            return steps.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.food_custom_steps, null);
            TextView tvNumber = (TextView) view.findViewById(R.id.tv_step_number);
            TextView tvStep = (TextView) view.findViewById(R.id.tv_step_step);

            tvNumber.setText("00");
            tvStep.setText(steps.get(i));

            return view;
        }
    }
}
