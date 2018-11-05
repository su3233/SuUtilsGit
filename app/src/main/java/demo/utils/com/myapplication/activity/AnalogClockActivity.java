package demo.utils.com.myapplication.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import demo.utils.com.myapplication.R;

public class AnalogClockActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDate, btnTime;
    private TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock);
        btnDate = (Button) findViewById(R.id.btnDatePickerDialog);
        btnTime = (Button) findViewById(R.id.btnTimePickerDialog);
        textClock = (TextClock) findViewById(R.id.tc_textclock);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);

        // 设置12时制显示格式
        textClock.setFormat12Hour("EEEE, MMMM dd, yyyy h:mmaa");
        // 设置24时制显示格式
//        textClock.setFormat24Hour("yyyy-MM-dd hh:mm:ss, EEEE");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDatePickerDialog:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(AnalogClockActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(AnalogClockActivity.this, year + "年 " + (monthOfYear + 1) + "月 " + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
                break;

            case R.id.btnTimePickerDialog:
                TimePickerDialog time = new TimePickerDialog(AnalogClockActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(AnalogClockActivity.this, hourOfDay + "点 " + minute + "分", Toast.LENGTH_SHORT).show();
                    }
                }, 18, 25, true);
                time.show();
                break;
        }

    }
}
