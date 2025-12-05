package com.example.chapitre2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListContact extends AppCompatActivity implements OnUserClickListener {
    private UserAdapter userAdapter;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listcontact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
Intent intent = getIntent();
int userId = intent.getIntExtra("USER_ID", -1);
String userName = intent.getStringExtra("USER_NAME");

        users = new ArrayList<>();
      /*  users.add(new User(1, "Ali Chouikhi"));
        users.add(new User(2, "Fatma Ben Salah"));
        users.add(new User(3, "Mohamed Trabelsi"));*/
        users.add(new User(userId, userName));

        userAdapter = new UserAdapter(users, this); // 'this' car l'Activity implémente l'interface
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onEditClick(User user) {
        String phoneNumber = "tel:99490095";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNumber));

        startActivity(intent);
        Toast.makeText(this, "Modification de " + user.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(User user, int position) {
        users.remove(position);
        userAdapter.notifyItemRemoved(position);
        // Important: Notifier aussi les items suivants pour mettre à jour leur position
        userAdapter.notifyItemRangeChanged(position, users.size());
finish();
        Toast.makeText(this, user.getName() + " a été supprimé", Toast.LENGTH_SHORT).show();
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() { return id; }
        public String getName() { return name; }
    }
}
