package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookreader.R;

public class PageFragment extends Fragment {
    // Store instance variables
    private String content;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static PageFragment newInstance(int page, String title) {
        PageFragment fragmentFirst = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        content = getArguments().getString("someTitle");
    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvPageNumber = (TextView) view.findViewById(R.id.tvPageNumber);
        tvPageNumber.setText(page + "");
        TextView tvContent = (TextView) view.findViewById(R.id.tvPageContent);
        tvContent.setText(content);
    }
    public int getMeasureTv (TextView tv) {
        int height = tv.getMeasuredHeight();
        int width = tv.getMeasuredWidth();
        //TODO: To be implemented
        return 200;
    }
}
