package app.mad.jobby;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FilterBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private ArrayList<String> listLokasi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);


        listLokasi = new ArrayList<>(getArguments().getStringArrayList("listLokasi"));
//        System.out.println(listLokasi);

        Spinner spinnerLokasi = v.findViewById(R.id.bsSpinnerLokasi);

        ArrayAdapter<String> lokasiAdapter = new ArrayAdapter<>(getActivity(), R.layout.style_spinner_text , listLokasi);
        spinnerLokasi.setAdapter(lokasiAdapter);

        spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), listLokasi.get(position) + " Selected", Toast.LENGTH_SHORT).show();
                mListener.onSpinnerItemSelected(listLokasi.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    public interface BottomSheetListener{
        void onSpinnerItemSelected(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement bottom sheet listener");
        }
    }
}
