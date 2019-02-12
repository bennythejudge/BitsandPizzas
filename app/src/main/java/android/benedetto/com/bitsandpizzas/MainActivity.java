package android.benedetto.com.bitsandpizzas;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // create a private variable for the ShareActionProvider
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the toolbar as the actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu - this add itmes to the app bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // find the action share item and add it to the private variable
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // call the method with a string to share
        setShareActionIntent("Want to join me for a pizza?");

        return super.onCreateOptionsMenu(menu);
    }

    // when an entry in the menu is clicked, we create an Intent
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text) {

        // create the intent for action send
        Intent intent = new Intent(Intent.ACTION_SEND);

        // specify the type
        intent.setType("text/plain");

        // put an extra in the intent: the text to send
        intent.putExtra(Intent.EXTRA_TEXT, text);

        // pass the intent to the share provider
        shareActionProvider.setShareIntent(intent);
    }
}
