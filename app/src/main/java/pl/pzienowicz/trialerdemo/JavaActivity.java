package pl.pzienowicz.trialerdemo;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import pl.pzienowicz.trialer.Trialer;
import pl.pzienowicz.trialer.action.DisplayToastAction;
import pl.pzienowicz.trialer.listener.TrialEndedListener;
import pl.pzienowicz.trialer.validator.InstallDateValidator;
import pl.pzienowicz.trialer.validator.StaticDateValidator;
import pl.pzienowicz.trialer.validator.ValidatorInterface;

public class JavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar someFutureDate = Calendar.getInstance();
        someFutureDate.add(Calendar.DATE, 30);

        Trialer trialer = new Trialer(this);
        trialer.setTrialEndedListener(new TrialEndedListener() {
            @Override
            public void onTrialEnded(@NotNull ValidatorInterface validatorClass) {
                Toast.makeText(getApplicationContext(), "Trial ended by: " + validatorClass.getClass().getSimpleName()  , Toast.LENGTH_LONG).show();
            }
        });
        trialer.addValidator(new InstallDateValidator(10));
        trialer.addValidator(new StaticDateValidator(someFutureDate.getTime()));
        trialer.addAction(new DisplayToastAction(this, "Toast by DisplayToastAction"));
        trialer.valid();
    }
}
