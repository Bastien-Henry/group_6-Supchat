package fr.supinternet.supchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText userNameEdit;
	private EditText passwordEdit;
	
	private Button validateBtn;
	private Button goToCreateBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();
    }
    
    private void initView() {
    	userNameEdit = (EditText) findViewById(R.id.activity_login_password);
    	passwordEdit = (EditText) findViewById(R.id.activity_login_password);
    	
    	validateBtn = (Button) findViewById(R.id.activity_login_validate);
    	goToCreateBtn = (Button) findViewById(R.id.activity_login_go_to_create);
    	
    	goToCreateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToCreateActivity();
			}
		});
    }
    
    private void goToCreateActivity(){
    	Intent intent = new Intent(this, CreateAccountActivity.class);
    	startActivity(intent);
    }
}
