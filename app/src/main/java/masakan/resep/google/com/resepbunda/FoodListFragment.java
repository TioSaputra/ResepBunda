package masakan.resep.google.com.resepbunda;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FoodListFragment extends Fragment implements AdapterView.OnItemClickListener {
    Intent i;
    String foodId;
    String foodName;
    int image[] = {R.drawable.avatar, R.drawable.avatar, R.drawable.avatar};
    ListView listview;


    ArrayList<String> foodNames = null;
    ArrayList<String> idFood = null;

    DatabaseReference myRef;
    FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_food_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("foodlist");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                collectNames((Map<String,Object>) dataSnapshot.getValue());
                fillFoodList();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    private void fillFoodList(){
        listview = (ListView) getView().findViewById(R.id.Listview);
        Customadapter customadapter = new Customadapter();
        listview.setAdapter(customadapter);
        listview.setOnItemClickListener(this);
    }
    private void collectNames(Map<String,Object> users) {
        foodNames = new ArrayList<>();
        idFood = new ArrayList<>();
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){
            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            foodNames.add((String) singleUser.get("nama"));
            idFood.add((String) singleUser.get("id"));
        }

        System.out.println(" this is food id" + idFood);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        i = new Intent(getActivity(), FoodDetailActivity.class);
        foodId = ((TextView) view.findViewById(R.id.hidden_id)).getText().toString();
        foodName = ((TextView) view.findViewById(R.id.hidden_id)).getText().toString();
        i.putExtra("id", foodId);
        i.putExtra("name", foodName);
        startActivity(i);
    }


    class Customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return foodNames.size();
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
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.food_custom_list, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.food_image);
            TextView names = (TextView) view.findViewById(R.id.food_name);
            TextView hidden = (TextView) view.findViewById(R.id.hidden_id);

            imageView.setImageResource(image[i]);
            names.setText(foodNames.get(i).toString());
            hidden.setText(idFood.get(i).toString());
            return view;
        }
    }
}