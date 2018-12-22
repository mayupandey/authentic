package predic.com.authentic;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Main2Activity extends AppCompatActivity {
   RecyclerView mRecylerView;
   FirebaseDatabase mfirebaseDatabase;
   DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecylerView=findViewById(R.id.recylerView);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
         mfirebaseDatabase=FirebaseDatabase.getInstance();
         mRef=mfirebaseDatabase.getReference("TEAMS");
    }

    @Override
    protected void onStart() {
        super.onStart();
       FirebaseRecyclerAdapter<Model ,ViewHolder> firebaseRecyclerAdapter=
               new FirebaseRecyclerAdapter<Model, ViewHolder>(
                       Model.class,
                       R.layout.row,
                       ViewHolder.class,
                       mRef
               ) {
                   @Override
                   protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                       viewHolder.setDetails(getApplicationContext(),model.getDescription(),model.getTeamname(),model.getImage());
                   }
               };
       mRecylerView.setAdapter(firebaseRecyclerAdapter);
    }
}
