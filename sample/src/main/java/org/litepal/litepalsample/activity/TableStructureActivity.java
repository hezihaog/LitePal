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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.litepalsample.R;
import org.litepal.tablemanager.Connector;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.DBUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 表结构信息
 */
public class TableStructureActivity extends AppCompatActivity {
    public static final String TABLE_NAME = "table_name";

    /**
     * 准备要分析的表名
     */
    private String mTableName;

    private final List<ColumnModel> mList = new ArrayList<>();

    public static void actionStart(Context context, String tableName) {
        Intent intent = new Intent(context, TableStructureActivity.class);
        intent.putExtra(TABLE_NAME, tableName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.table_structure_layout);
        //获取准备要分析的表名
        mTableName = getIntent().getStringExtra(TABLE_NAME);
        ListView mTableStructureListView = findViewById(R.id.table_structure_listview);
        //分析表结构
        analyzeTableStructure();
        ArrayAdapter<ColumnModel> mAdapter = new MyArrayAdapter(this, 0, mList);
        mTableStructureListView.setAdapter(mAdapter);
    }

    /**
     * 分析表结构
     */
    private void analyzeTableStructure() {
        //查询表结构，并转换为对象
        TableModel tableMode = DBUtility.findPragmaTableInfo(mTableName, Connector.getDatabase());
        //获取表的所有字段
        Collection<ColumnModel> columnModelList = tableMode.getColumnModels();
        mList.addAll(columnModelList);
    }

    static class MyArrayAdapter extends ArrayAdapter<ColumnModel> {
        public MyArrayAdapter(Context context, int textViewResourceId, List<ColumnModel> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ColumnModel columnModel = getItem(position);
            //字段名
            String columnName = columnModel.getColumnName();
            //字段类型
            String columnType = columnModel.getColumnType();
            //是否可空
            boolean nullable = columnModel.isNullable();
            //是否唯一
            boolean unique = columnModel.isUnique();
            //是否存在索引
            boolean hasIndex = columnModel.hasIndex();
            //默认值
            String defaultValue = columnModel.getDefaultValue();
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.table_structure_item, null);
            } else {
                view = convertView;
            }
            TextView text1 = view.findViewById(R.id.text_1);
            text1.setText(columnName);
            TextView text2 = view.findViewById(R.id.text_2);
            text2.setText(columnType);
            TextView text3 = view.findViewById(R.id.text_3);
            text3.setText(String.valueOf(nullable));
            TextView text4 = view.findViewById(R.id.text_4);
            text4.setText(String.valueOf(unique));
            TextView text5 = view.findViewById(R.id.text_5);
            text5.setText(defaultValue);
            TextView text6 = view.findViewById(R.id.text_6);
            text6.setText(String.valueOf(hasIndex));
            return view;
        }
    }
}