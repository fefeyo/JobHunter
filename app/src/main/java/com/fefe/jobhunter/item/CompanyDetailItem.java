package com.fefe.jobhunter.item;

import com.activeandroid.annotation.Column;

/**
 * Created by USER on 2015/05/18.
 */
public class CompanyDetailItem {

    /*　会社名　*/
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /*　部署名　*/
    private String position;
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }

    /*　説明会　*/
    private int guidance_month;
    private int guidance_day;
    private String guidance_time;
    private String guidance_place;
    private boolean used_guidance;

    /*　エントリーシート　*/
    private int entryseat_start_month;
    private int entryseat_start_day;
    private int entryseat_end_month;
    private int entryseat_end_day;
    private String entryseat_system;
    private String entryseat_contains;
    private boolean used_entryseat;

    /*　履歴書　*/
    private int personal_start_month;
    private int personal_start_day;
    private int personal_end_month;
    private int personal_end_day;
    private String personalseat_system;
    private String personalseat_format;
    private boolean used_personalseat;

    /*　グループディスカッション　*/
    private int groupdiscussion_month;
    private int groupdiscussion_day;
    private String groupdiscussion_time;
    private String groupdiscussion_place;
    private String groupdiscussion_clothes;
    private boolean used_groupdiscussion;

    /*　１次面接　*/
    private int interview_month_one;
    private int interview_day_one;
    private String interview_time_one;
    private String interview_place_one;
    private String interview_format_one;
    private boolean interview_person_student_one;
    private boolean interview_person_cto_one;
    private boolean interview_person_ceo_one;
    private boolean interview_person_hr_one;
    private String interview_clothes_one;
    private boolean used_interview_one;

    /*　２次面接　*/
    private int interview_month_twe;
    private int interview_day_twe;
    private String interview_time_twe;
    private String interview_place_twe;
    private String interview_format_twe;
    private boolean interview_person_student_twe;
    private boolean interview_person_cto_twe;
    private boolean interview_person_ceo_twe;
    private boolean interview_person_hr_twe;
    private String interview_clothes_twe;
    private boolean used_interview_twe;

    /*　３次面接　*/
    private int interview_month_three;
    private int interview_day_three;
    private String interview_time_three;
    private String interview_place_three;
    private String interview_format_three;
    private boolean interview_person_student_three;
    private boolean interview_person_cto_three;
    private boolean interview_person_ceo_three;
    private boolean interview_person_hr_three;
    private String interview_clothes_three;
    private boolean used_interview_three;

    /*　４次面接　*/
    private int interview_month_four;
    private int interview_day_four;
    private String interview_time_four;
    private String interview_place_four;
    private String interview_format_four;
    private boolean interview_person_student_four;
    private boolean interview_person_cto_four;
    private boolean interview_person_ceo_four;
    private boolean interview_person_hr_four;
    private String interview_clothes_four;
    private boolean used_interview_four;

    /*　５次面接　*/
    private int interview_month_five;
    private int interview_day_five;
    private String interview_time_five;
    private String interview_place_five;
    private String interview_format_five;
    private boolean interview_person_student_five;
    private boolean interview_person_cto_five;
    private boolean interview_person_ceo_five;
    private boolean interview_person_hr_five;
    private String interview_clothes_five;
    private boolean used_interview_five;

    /*　最終面接　*/
    private int interview_month_final;
    private int interview_day_final;
    private String interview_time_final;
    private String interview_place_final;
    private String interview_format_final;
    private boolean interview_person_student_final;
    private boolean interview_person_cto_final;
    private boolean interview_person_ceo_final;
    private boolean interview_person_hr_final;
    private String interview_clothes_final;
    private boolean used_interview_final;

    /*　エントリー締め切り　*/
    private int entryperiod_month;
    private int entryperiod_day;
    private String entryperiod_system;
    private String entryperiod_format;
    private boolean used_entryperiod;

    /*　登録日時　*/
    private String time;

    /*　ラベルカラー　*/
    private String color;

}
