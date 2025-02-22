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

import org.litepal.litepalsample.R;

/**
 * 管理表
 */
public class ManageTablesActivity extends AppCompatActivity implements OnClickListener {
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ManageTablesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_tables_layout);
        Button mCurrentModelStructureBtn = findViewById(R.id.current_model_structure_btn);
        Button mOperateDatabaseBtn = findViewById(R.id.operate_database_btn);
        mCurrentModelStructureBtn.setOnClickListener(this);
        mOperateDatabaseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.current_model_structure_btn:
                //当前模型结构
                ModelListActivity.actionStart(this);
                break;
            case R.id.operate_database_btn:
                //当前表结构
                TableListActivity.actionStart(this);
                break;
            default:
                break;
        }
    }
}