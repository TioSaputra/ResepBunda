package masakan.resep.google.com.resepbunda.temporary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import masakan.resep.google.com.resepbunda.R;

/**
 * Created by tio on 1/1/18.
 */

public class Fragment2Test extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdua, container, false);
    }
}