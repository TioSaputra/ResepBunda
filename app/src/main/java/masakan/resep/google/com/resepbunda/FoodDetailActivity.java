package masakan.resep.google.com.resepbunda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView txtFoodName;
    String foodId;

    ArrayList<String> steps;

    ListView lvSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        txtFoodName = (TextView) findViewById(R.id.food_detail_name);


        Intent i = getIntent();
        foodId = i.getStringExtra("id");
        String foodName = i.getStringExtra("name");

        txtFoodName.setText(foodName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foodsteps").child(foodId);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object value = dataSnapshot.getValue();
                if(value instanceof List) {
                    steps = (ArrayList<String>) value;
                    // do your magic with values
                    System.out.println("Its a list");
                    System.out.println("The Second value " + steps.get(1));
                    fillList();
                }
                else {
                    // handle other possible types
                    System.out.println("ITs not a list");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    public void fillList(){
        lvSteps = (ListView) findViewById(R.id.lvsteps);
        CustomadapterStep customadapter = new CustomadapterStep();
        lvSteps.setAdapter(customadapter);
        lvSteps.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
