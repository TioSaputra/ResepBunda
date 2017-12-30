package masakan.resep.google.com.resepbunda;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

public class FoodDetailActivity extends AppCompatActivity {

    TextView txtFoodName;
    String foodId;

    ArrayList<String> steps;
    ArrayList<String> ingredient;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    FoodDetailStepsFragment stepsFragment;
    FoodDetailRecipeFragment ingredientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        txtFoodName = (TextView) findViewById(R.id.food_detail_name);
        stepsFragment = new FoodDetailStepsFragment();
        ingredientFragment = new FoodDetailRecipeFragment();

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
                    stepsFragment.setSteps(steps);
                    // do your magic with values
                    System.out.println("Its a list");
                    System.out.println("The Second value " + steps.get(1));
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

        myRef = database.getReference("ingredient").child(foodId);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object value = dataSnapshot.getValue();
                if(value instanceof List) {
                    ingredient = (ArrayList<String>) value;
                    ingredientFragment.setSteps(ingredient);
                    // do your magic with values
                    System.out.println("Its a list");
                    System.out.println("The Second value " + ingredient.get(1));
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

        viewPager = (ViewPager) findViewById(R.id.food_detail_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.food_detail_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(stepsFragment, "Steps");
        adapter.addFragment(ingredientFragment, "Recipe");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
