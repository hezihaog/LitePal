/*
 * Copyright (C)  Tony Green, Litepal Framework Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litepal.litepalsample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.litepalsample.R;
import org.litepal.litepalsample.model.Singer;

/**
 * 聚合函数-最大值
 */
public class MaxSampleActivity extends AppCompatActivity implements OnClickListener {
    private EditText mAgeEdit;

    private TextView mResultText;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MaxSampleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_sample_layout);
        Button mMaxBtn1 = findViewById(R.id.max_btn1);
        Button mMaxBtn2 = findViewById(R.id.max_btn2);
        mAgeEdit = findViewById(R.id.age_edit);
        mResultText = findViewById(R.id.result_text);
        mMaxBtn1.setOnClickListener(this);
        mMaxBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int result = 0;
        switch (view.getId()) {
            case R.id.max_btn1:
                //找出最大年龄
                result = LitePal.max(Singer.class, "age", Integer.TYPE);
                mResultText.setText(String.valueOf(result));
                break;
            case R.id.max_btn2:
                //找出年龄小于23岁的人中的最大年龄的人的年龄
                try {
                    result = LitePal.where("age < ?", mAgeEdit.getText().toString()).max(
                            Singer.class, "age", Integer.TYPE);
                    mResultText.setText(String.valueOf(result));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }
}