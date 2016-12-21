package com.kinstalk.android.demo.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kinstalk.android.utils.TextAutoFitUtils;
import com.siqing.demotest.R;

/**
 * Created by siqing on 16/1/18.
 */
public class TextComputeFragment extends BaseFragment {
    public static final String PARAM1 = "TextComputeFragment";
    public static final String content = "如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize如何在一个盒内绘制文本，在canvas上与 DynamicLayout 和 Ellipsize";
    private EditText contentEdit;
    private TextView textView;

    public static TextComputeFragment newInstance(String arg1) {
        TextComputeFragment computeFragment = new TextComputeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM1, arg1);
        computeFragment.setArguments(bundle);
        return computeFragment;
    }

    @Override
    protected View initView(ViewGroup container, Bundle savedInstanceState) {
        View root = mInflater.inflate(R.layout.fragment_text_compute, null);
        textView = (TextView) root.findViewById(R.id.content_text);
        contentEdit = (EditText) root.findViewById(R.id.content_edit);
        textView.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }

    @Override
    protected void initActions(View rootView) {
        super.initActions(rootView);
        contentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(contentEdit.getText())) {
                    return;
                }
                textView.setText(s.toString());
                int width = (int) (textView.getWidth() - TextAutoFitUtils.dp2px(getActivity(), 5) * 2);
                int height = (int) (textView.getHeight() - TextAutoFitUtils.dp2px(getActivity(), 5) * 2);
                float textSize = TextAutoFitUtils.computeTextSize(getActivity(), textView.getText(), width, height, textView.getTextSize(), TextAutoFitUtils.sp2px(getActivity(), 20));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
        });
    }

}
