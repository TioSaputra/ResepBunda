package masakan.resep.google.com.resepbunda;

import android.content.Intent;
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

public class FoodDetailRecipeFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView lvIngredient;
    ArrayList<String> steps;
    public FoodDetailRecipeFragment(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.food_detail_recipe_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void fillList(){
        lvIngredient = (ListView) getView().findViewById(R.id.lvingredient);
        CustomadapterIngredient customadapter = new CustomadapterIngredient();
        lvIngredient.setAdapter(customadapter);
        lvIngredient.setOnItemClickListener(this);
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

    class CustomadapterIngredient extends BaseAdapter {

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
