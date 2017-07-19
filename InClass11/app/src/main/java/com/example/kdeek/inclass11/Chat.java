//package com.example.kdeek.inclass11;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.media.Image;
//import android.net.Uri;
//import android.os.Message;
//import android.provider.ContactsContract;
//import android.provider.MediaStore;
//import android.support.annotation.ArrayRes;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageMetadata;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//
//public class Chat extends AppCompatActivity {
//    Context context = this;
//    String comment = "";
//    public static HashMap<String,String> cmtsList = new HashMap<String,String>() ;
//    String Name;
//    ListView msL;
//    TextView nameView;
//    ImageView addMsg,logout;
//    //public static String uName = "NA";
//    ArrayList<String> cmts = new ArrayList<String>();
//    ImageView gal;
//    EditText msgText;
//    ArrayList<DataItem> messages = new ArrayList<DataItem>();
//
//    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//    FirebaseStorage storage = FirebaseStorage.getInstance();
//
//    DatabaseReference myRef = mDatabase.getReference("Msgs");
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//
//        if(getIntent().getExtras()!=null){
//            Name = getIntent().getExtras().getString("name");
//        }
//        nameView = (TextView) findViewById(R.id.unameL);
//        //msL = (ListView) findViewById(R.id.listView) ;
//
//
//        addMsg = (ImageView) findViewById(R.id.addMsg);
//        logout = (ImageView) findViewById(R.id.logout);
//        msgText = (EditText) findViewById(R.id.msg);
//        gal = (ImageView) findViewById(R.id.addPhoto);
//
//       nameView.setText(EmailPasswordActivity.curUser.substring(0,5));
//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent returnIntent = new Intent();
//                setResult(Activity.RESULT_CANCELED,returnIntent);
//                finish();
//            }
//        });
//
//        gal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"),900);
//            }
//        });
//
//
//        addMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                Calendar c = Calendar.getInstance();
////                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
////                String formattedDate = df.format(c.getTime());
//
//                //   public Msg(String auther, String imageURL, String message, String time) {
//
//                long time= System.currentTimeMillis();
//
//                Msg msg = new Msg(EmailPasswordActivity.curUser, "", msgText.getText().toString(), time+"");
//                msg.id = EmailPasswordActivity.curUser +((int)(Math.random()*100000));
//                myRef.child(EmailPasswordActivity.curUser).child(msg.id).setValue(msg);
//
//            }
//        });
//
//
//
//
//
//
//    }
//
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 900)
//        {
//            if (resultCode == Activity.RESULT_OK)
//            {
//                if (data != null)
//                {
//                    try
//                    {
//
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
//                        byte[] databyte = baos.toByteArray();
//
//                        String path = "FireMemes/" + UUID.randomUUID() + ".png";
//                        StorageReference storageReference = storage.getReference(path);
//
//                        //StorageMetadata metadata = new StorageMetadata.Builder().setCustomMetadata("text", OverlayText.getText().toString()).build();
//                        UploadTask uploadTask = storageReference.putBytes(databyte);
//                        uploadTask.addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Chat.this, "Upload Failed",Toast.LENGTH_LONG ).show();
//                            }
//                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                Uri uri = taskSnapshot.getDownloadUrl();
//                                String imageURL = uri.toString();
//                                long time= System.currentTimeMillis();
//                                Msg msg = new Msg(EmailPasswordActivity.curUser, imageURL, "", time+"");
//                                msg.id = EmailPasswordActivity.curUser +((int)(Math.random()*100000));
//                                myRef.child(EmailPasswordActivity.curUser).child(msg.id).setValue(msg);
//                            }
//                        });
//
//
//
//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED)
//            {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                messages.clear();
//                Log.d("demo123",dataSnapshot.toString());
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    // TODO: handle the post
//                    Log.d("demo123",postSnapshot.toString());
//                    //if(postSnapshot.getKey().equals(EmailPasswordActivity.curUser)){
//                        for (DataSnapshot expenses: postSnapshot.getChildren()){
//                            Log.d("demo1234",expenses.getValue().toString());
//                            messages.add(expenses.getValue(Msg.class));
//                        }
//                    //}
//                }
//
//
//
//                RecyclerView msgs = (RecyclerView) findViewById(R.id.fromlinkrV);
//                recyclerAdapter adapter1 = new recyclerAdapter(Chat.this, messages, new recyclerAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(int position) {
//                        Toast.makeText(Chat.this,"DELETED",Toast.LENGTH_SHORT).show();
//                        myRef.child(EmailPasswordActivity.curUser).child(messages.get(position).id).removeValue();
//                    }
//                }, new recyclerAdapter.OnCommentClickListener() {
//                    @Override
//                    public void onCommentClicked(final int position) {
//                        Toast.makeText(Chat.this,"COMMENT",Toast.LENGTH_SHORT).show();
//                        LayoutInflater li = LayoutInflater.from(context);
//                        View promptsView = li.inflate(R.layout.prompt, null);
//
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                                context);
//
//                        // set prompts.xml to alertdialog builder
//                        alertDialogBuilder.setView(promptsView);
//
//                        final EditText userInput = (EditText) promptsView
//                                .findViewById(R.id.editTextDialogUserInput);
//
//                        // set dialog message
//                        alertDialogBuilder
//                                .setCancelable(false)
//                                .setPositiveButton("OK",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog,int id) {
//                                                String msgId = messages.get(position).id;
//
//                                                long time= System.currentTimeMillis();
//                                                DatabaseReference myCMTRef = mDatabase.getReference("Cmts");
//                                               String cmtID = EmailPasswordActivity.curUser +((int)(Math.random()*100000));
//                                                myCMTRef.child(msgId).child(cmtID).setValue(userInput.getText().toString());
//
//                                            }
//                                        })
//                                .setNegativeButton("Cancel",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int id) {
//                                                dialog.cancel();
//                                            }
//                                        });
//
//                        // create alert dialog
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//
//                        // show it
//                        alertDialog.show();
//
//
//                    }
//                });
//                msgs.setAdapter(adapter1);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this, LinearLayoutManager.VERTICAL, false);
//                msgs.setLayoutManager(layoutManager);
//
//
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//
//
//
//
//        DatabaseReference myCRef = mDatabase.getReference("Cmts");
//        myCRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Log.d("Comments",dataSnapshot.toString());
//                for(DataSnapshot cmts : dataSnapshot.getChildren()){
//                    Log.d("Comments1",cmts.toString());
//
//                        for(DataSnapshot cmtsOfthismsg : cmts.getChildren()){
//                            cmtsList.put(cmts.getKey(),(String)cmtsOfthismsg.getValue().toString());
//                        }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }
//
//
//
//}
