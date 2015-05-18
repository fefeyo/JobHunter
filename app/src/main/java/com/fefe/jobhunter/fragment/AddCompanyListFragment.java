package com.fefe.jobhunter.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.fefe.jobhunter.AddCompanyActivity;

/**
 * Created by USER on 2015/05/03.
 */
public class AddCompanyListFragment extends DialogFragment{
    private AddCompanyActivity activity;
    private String[] items;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        activity = (AddCompanyActivity)getActivity();
        items = getItems();
        if(activity.count > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("選考を追加する");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (items[which]) {
                        case "説明会":
                            activity.setGuidance();
                            activity.isGuidance = true;
                            activity.map.remove(0);
                            activity.count--;
                            break;
                        case "エントリーシート":
                            activity.setEntrySeat();
                            activity.isEntrySeat = true;
                            activity.map.remove(1);
                            activity.count--;
                            break;
                        case "履歴書":
                            activity.setPersonalSeat();
                            activity.isPersonalSeat = true;
                            activity.map.remove(2);
                            activity.count--;
                            break;
                        case "グループディスカッション":
                            activity.setGroupDiscussion();
                            activity.isGroupDiscussion = true;
                            activity.map.remove(3);
                            activity.count--;
                            break;
                        case "面接":
                            if(activity.interview_count < 6) {
                                activity.setInterview(activity.interview_count + "次");
                                activity.interview_count++;
                                if(activity.interview_count == 6){
                                    activity.isInterview = true;
                                    activity.map.remove(4);
                                    activity.count--;
                                }
                            }
                            break;
                        case "エントリー締め切り":
                            activity.setEntryPeriod();
                            activity.isEntryPeriod = true;
                            activity.map.remove(6);
                            activity.count--;
                            break;
                        case "最終面接":
                            activity.setFinalInterview();
                            activity.map.remove(5);
                            activity.count--;
                            break;
                    }
                }
            });
            return builder.create();
        }else{
            return Alert();
        }
    }
    private String[] getItems(){
        String[] items = new String[activity.count];
        int n = 0;
        for(int i = 0; i < 7; i++){
            if(activity.map.containsKey(i)){
                items[n] = activity.map.get(i);
                n++;
            }
        }
        return items;
    }

    private AlertDialog Alert(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("選考がありません！");
        dialog.setMessage("もう追加できる選考がありません！\n編集を終了しますか？");
        dialog.setPositiveButton("はい",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.saveToSQL();
            }
        });
        dialog.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        return dialog.create();
    }

}
