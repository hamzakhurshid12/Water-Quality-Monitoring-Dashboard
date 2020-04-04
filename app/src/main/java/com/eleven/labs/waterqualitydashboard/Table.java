package com.eleven.labs.waterqualitydashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.univocity.parsers.common.processor.ColumnProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class Table extends AppCompatActivity {

    List csvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //Getting Pre-Stored Data
        if(csvData==null) {
            InputStream csvInputStream = getResources().openRawResource(R.raw.parameters);
            CSVFile csvfile = new CSVFile(csvInputStream);
            csvData = csvfile.read();
        }

        DataTable dataTable = findViewById(R.id.data_table);

        DataTableHeader header = new DataTableHeader.Builder()
                .item("Date", 2)
                .item("Temperature", 1)
                .item("Turbidity", 1)
                .item("pH", 1)
                .item("Conductivity", 1)
                .item("Dissolved Oxygen", 1)
                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        // define 200 fake rows for table
        for(int i=1;i<csvData.size();i++){
            //Random r = new Random();
            //int random = r.nextInt(i+1);
            //int randomDiscount = r.nextInt(20);
            DataTableRow row = new DataTableRow.Builder()
                    .value(String.valueOf(((String[])csvData.get(i))[1]))
                    .value(String.valueOf(((String[])csvData.get(i))[2]))
                    .value(String.valueOf(((String[])csvData.get(i))[3]))
                    .value(String.valueOf(((String[])csvData.get(i))[4]))
                    .value(String.valueOf(((String[])csvData.get(i))[5]))
                    .value(String.valueOf(((String[])csvData.get(i))[6]))
                    .build();
            rows.add(row);
        }

        //dataTable.setTypeface(typeface);
        dataTable.setHeader(header);
        dataTable.setRows(rows);
        dataTable.inflate(getApplicationContext());

        //getHistoricalCSV();
        Toast.makeText(this, "CSV Length: "+csvData.size(), Toast.LENGTH_SHORT).show();

    }


}

class CSVFile {
    InputStream inputStream;
    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public List read(){
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}
