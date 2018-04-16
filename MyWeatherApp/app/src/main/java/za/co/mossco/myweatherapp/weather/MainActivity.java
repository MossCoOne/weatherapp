package za.co.mossco.myweatherapp.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.Objects;

import za.co.mossco.myweatherapp.R;

public class MainActivity extends AppCompatActivity {

    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        TextView toolBarTitle = findViewById(R.id.tv_tool_bar_title);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        toolBarTitle.setText(R.string.weekly_weather);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
